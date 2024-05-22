package com.pquinci.ualabackendchallenge.service;

import com.pquinci.ualabackendchallenge.dto.CreateUserDTO;
import com.pquinci.ualabackendchallenge.model.User;

public interface UserService {

    String createUser(CreateUserDTO request);
}
