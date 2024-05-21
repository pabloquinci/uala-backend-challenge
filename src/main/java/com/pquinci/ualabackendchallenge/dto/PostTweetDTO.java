package com.pquinci.ualabackendchallenge.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PostTweetDTO implements Serializable {

    private String username;
    private String text;

}
