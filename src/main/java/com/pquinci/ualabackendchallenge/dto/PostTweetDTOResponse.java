package com.pquinci.ualabackendchallenge.dto;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostTweetDTOResponse implements Serializable {

    private Long id;
    private String username;
    private String text;
}
