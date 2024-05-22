package com.pquinci.ualabackendchallenge.service;

import com.pquinci.ualabackendchallenge.dto.*;
import com.pquinci.ualabackendchallenge.model.Tweet;

import java.util.List;
import java.util.Optional;

public interface TweetsService {

    Optional<PostTweetDTOResponse> post(PostTweetDTO request);

    PostTweetDTOResponse findById(Long id);

    String follow(FollowDTORequest request);

    TimelineUsuario followersTweets(String username);



}
