package com.pquinci.ualabackendchallenge.controller;

import com.pquinci.ualabackendchallenge.dto.PostTweetDTO;
import com.pquinci.ualabackendchallenge.dto.PostTweetDTOResponse;
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


    @GetMapping("/findTweet/{id}")
    public ResponseEntity<PostTweetDTOResponse> post(@PathVariable(name="id") Long id){
        ClassLoader classLoader= Tweet.class.getClassLoader();

        System.out.println(classLoader);

        PostTweetDTOResponse response=tweetsService.findById(id);


     //   if(response.isPresent()){
            //return ResponseEntity.ok(response.get());
       //     PostTweetDTOResponse postResp=response.get();
            Tweet twit= Tweet.builder().text(response.getText()).build();
            return ResponseEntity.ok(response);
       // }

       // return ResponseEntity.internalServerError().build();

    }

}
