package com.pquinci.ualabackendchallenge.service.serviceImpl;

import com.pquinci.ualabackendchallenge.configuration.CacheConfig;
import com.pquinci.ualabackendchallenge.dto.PostTweetDTO;
import com.pquinci.ualabackendchallenge.dto.PostTweetDTOResponse;
import com.pquinci.ualabackendchallenge.model.Tweet;
import com.pquinci.ualabackendchallenge.repository.TweetsRepository;
import com.pquinci.ualabackendchallenge.service.TweetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TweetsServiceImpl implements TweetsService {

    private Logger LOGGER = LoggerFactory.getLogger(TweetsServiceImpl.class);

    @Autowired
    TweetsRepository tweetsRepository;

    @Autowired
    public TweetsServiceImpl(TweetsRepository tweetsRepository){
        this.tweetsRepository=tweetsRepository;
    }

    @Override
    public Optional<PostTweetDTOResponse> post(PostTweetDTO request) {
        Tweet tweet= Tweet.builder()
                .username(request.getUsername())
                .text(request.getText())
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
                    .build();
         //   return Optional.of(respDTO);
            return respDTO;
        }
        return null;
    }

}
