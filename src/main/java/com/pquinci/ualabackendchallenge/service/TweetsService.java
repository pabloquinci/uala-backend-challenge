package com.pquinci.ualabackendchallenge.service;

import com.pquinci.ualabackendchallenge.dto.PostTweetDTO;
import com.pquinci.ualabackendchallenge.dto.PostTweetDTOResponse;
import com.pquinci.ualabackendchallenge.model.Tweet;

import java.util.Optional;

public interface TweetsService {

    Optional<PostTweetDTOResponse> post(PostTweetDTO request);

    PostTweetDTOResponse findById(Long id);


}
