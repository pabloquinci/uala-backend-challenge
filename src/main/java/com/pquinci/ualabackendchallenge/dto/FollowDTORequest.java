package com.pquinci.ualabackendchallenge.dto;

import jakarta.annotation.Nullable;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTORequest {

    @NonNull
    private String userFollower;
    @NonNull
    private String userFollowed;
}
