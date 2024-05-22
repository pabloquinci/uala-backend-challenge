package com.pquinci.ualabackendchallenge.controller;

import com.pquinci.ualabackendchallenge.dto.CreateUserDTO;
import com.pquinci.ualabackendchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO request){

        return  ResponseEntity.ok(userService.createUser(request));
    }
}
