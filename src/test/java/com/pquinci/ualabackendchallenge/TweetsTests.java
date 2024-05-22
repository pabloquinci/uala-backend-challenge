package com.pquinci.ualabackendchallenge;

import com.pquinci.ualabackendchallenge.dto.FollowDTORequest;
import com.pquinci.ualabackendchallenge.dto.PostTweetDTOResponse;
import com.pquinci.ualabackendchallenge.model.Tweet;
import com.pquinci.ualabackendchallenge.model.User;
import com.pquinci.ualabackendchallenge.repository.TweetsRepository;
import com.pquinci.ualabackendchallenge.repository.UserRepository;
import com.pquinci.ualabackendchallenge.service.serviceImpl.TweetsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
//@SpringBootTest


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TweetsTests {

    TweetsServiceImpl tweetsServiceImpl;

    @MockBean
    private UserRepository userRepository;

    User user;
    User userFollowed;

    PostTweetDTOResponse postTweetDTOResponse= PostTweetDTOResponse.builder()
            .text("Twit de pipito")
            .id(1L)
            .build();

    String resultadoFollow="UsuariopquinciSiguiendo a chaboncito";

    FollowDTORequest followDTORequest=FollowDTORequest.builder()
            .userFollower("pquinci")
            .userFollowed("chaboncito")
            .build();

    Tweet tweet;
    PostTweetDTOResponse findResponse;

    @BeforeEach
    void init() {

        user= User.builder()
                .userId(3l)
                .username("pquinci")
                .lastname("Quinci")
                .name("Quinci")
                .follows(new ArrayList<>())
                .build();
        userFollowed= User.builder()
                .userId(2L)
                .username("chaboncito")
                .lastname("Chaboncito")
                .name("Chaboncito")
                .follows(new ArrayList<>())
                .build();

        findResponse= PostTweetDTOResponse.builder().id(1L).text("Mock tweet").build();

        tweet= Tweet.builder().user(user).id(1L).text("Mock tweet").build();

    }

    @Test
    void findByIdOk(@Mock UserRepository userRepository, @Mock TweetsRepository tweetsRepository) {
        tweetsServiceImpl = new TweetsServiceImpl(tweetsRepository, userRepository);

        lenient().when(tweetsRepository.findById(1L)).thenReturn(Optional.of(tweet));

        Optional<PostTweetDTOResponse> result= tweetsServiceImpl.findById(1L);

        Assertions.assertEquals(tweet.getText(),findResponse.getText());

    }

    @Test
    void follow(@Mock UserRepository userRepository, @Mock TweetsRepository tweetsRepository) {
        tweetsServiceImpl = new TweetsServiceImpl(tweetsRepository, userRepository);
        lenient().when(userRepository.findByUsername("pquinci")).thenReturn(Optional.of(user));
        lenient().when(userRepository.findByUsername("chaboncito")).thenReturn(Optional.of(userFollowed));

        Optional<String> result= tweetsServiceImpl.follow(followDTORequest);

    }
}
