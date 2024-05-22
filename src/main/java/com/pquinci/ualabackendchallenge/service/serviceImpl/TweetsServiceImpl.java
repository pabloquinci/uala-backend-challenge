package com.pquinci.ualabackendchallenge.service.serviceImpl;

import com.pquinci.ualabackendchallenge.configuration.CacheConfig;
import com.pquinci.ualabackendchallenge.dto.*;
import com.pquinci.ualabackendchallenge.exception.UserAlreadyFollowedException;
import com.pquinci.ualabackendchallenge.exception.UserNotFoundExcdeption;
import com.pquinci.ualabackendchallenge.model.Tweet;
import com.pquinci.ualabackendchallenge.model.User;
import com.pquinci.ualabackendchallenge.repository.TweetsRepository;
import com.pquinci.ualabackendchallenge.repository.UserRepository;
import com.pquinci.ualabackendchallenge.service.TweetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetsServiceImpl implements TweetsService {

    private Logger LOGGER = LoggerFactory.getLogger(TweetsServiceImpl.class);

    @Autowired
    TweetsRepository tweetsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public TweetsServiceImpl(TweetsRepository tweetsRepository){
        this.tweetsRepository=tweetsRepository;
    }

    @Override
    public Optional<PostTweetDTOResponse> post(PostTweetDTO request) {
        User user= userRepository.findByUsername(request.getUsername()).get();
        Tweet tweet= Tweet.builder()
                .username(request.getUsername())
                .text(request.getText())
                .fecha(new Date())
                .user(user)
                .build();
        tweetsRepository.save(tweet);
        return Optional.of(PostTweetDTOResponse.builder().text(tweet.getText()).build());
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
    public PostTweetDTOResponse findById(Long id) {

        Optional<Tweet> response= tweetsRepository.findById(id);

        if(response.isPresent()){
            PostTweetDTOResponse respDTO= PostTweetDTOResponse.builder()
                    .id(response.get().getId())
                    .text(response.get().getText())
                    .username(response.get().getUsername())
                    .fecha(response.get().getFecha())
                    .build();
         //   return Optional.of(respDTO);
            return respDTO;
        }
        return null;
    }

    @Override
    public String follow(FollowDTORequest request) {
        Optional<User> user=userRepository.findByUsername(request.getUserFollower());
        Optional<User> userFollowwed=userRepository.findByUsername(request.getUserFollowed());

        if(!user.isPresent() || !userFollowwed.isPresent()){
            throw new UserNotFoundExcdeption();
        }
        Boolean followed=user.get().getFollows().stream().anyMatch(u-> u.getUserId()==userFollowwed.get().getUserId());

        if(followed) throw new UserAlreadyFollowedException();

        user.get().getFollows().add(userFollowwed.get());
        userRepository.save(user.get());

        return "Usuario" +user.get().getUsername()+ "Siguiendo a "+ userFollowwed.get().getUsername();
    }

    @Override
    public TimelineUsuario followersTweets(String username) {
        Optional<User> user=userRepository.findByUsername(username);
        TimelineUsuario timeline= TimelineUsuario.builder().followers(new ArrayList<>()).build();

        user.get().getFollows().stream().forEach(follower->{
            FollowersTweetsDTO foll=FollowersTweetsDTO.builder().listTweets(new ArrayList<>()).username(follower.getUsername()).build();

            follower.getTweets().stream().forEach(tit->{
                TweetFollowerDTO twitFollo=TweetFollowerDTO.builder()
                        .text(tit.getText())
                        .fecha(tit.getFecha())
                        .id(tit.getId())
                        .username(tit.getUsername())
                        .build();
                foll.getListTweets().add(twitFollo);
            });

            timeline.getFollowers().add(foll);
        });
        return timeline;

    }

}
