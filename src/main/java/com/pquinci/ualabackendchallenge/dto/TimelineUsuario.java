package com.pquinci.ualabackendchallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimelineUsuario {

    List<FollowersTweetsDTO> followers=new ArrayList<>();
}
