package com.pquinci.ualabackendchallenge.controller;

import com.pquinci.ualabackendchallenge.dto.*;
import com.pquinci.ualabackendchallenge.model.Tweet;
import com.pquinci.ualabackendchallenge.service.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tweets")
public class TweetsController {


    @Autowired
    TweetsService tweetsService;

    @PostMapping("/post")
    public ResponseEntity<PostTweetDTOResponse> post(@RequestBody PostTweetDTO request){

        Optional<PostTweetDTOResponse> response=tweetsService.post(request);

        if(response.isPresent()){
            return ResponseEntity.ok(response.get());
        }

        return ResponseEntity.internalServerError().build();

    }


    @PutMapping("/follow")
    public ResponseEntity<String> follow(@RequestBody FollowDTORequest request){

        Optional<String> response=tweetsService.follow(request);

        if (response.isPresent()){
            return ResponseEntity.ok(response.get());

        }
        return ResponseEntity.internalServerError().build();



    }


    @GetMapping("/findTweet/{id}")
    public ResponseEntity<PostTweetDTOResponse> post(@PathVariable(name="id") Long id){

        Optional<PostTweetDTOResponse> response=tweetsService.findById(id);

        if(response.isPresent()){
            Tweet twit= Tweet.builder().text(response.get().getText())
                    .username(response.get().getUsername())
                    //.fecha(response.getFecha())
                    .build();
            return ResponseEntity.ok(response.get());
        }
        return ResponseEntity.internalServerError().build();

    }

    @GetMapping("/getTimeline/{username}")
    public ResponseEntity<TimelineUsuario> getTimeline(@PathVariable(name="username") String username){
        TimelineUsuario response=tweetsService.followersTweets(username);

        return ResponseEntity.ok(response);


    }




}
