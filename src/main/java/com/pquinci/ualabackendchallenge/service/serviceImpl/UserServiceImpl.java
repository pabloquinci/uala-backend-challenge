package com.pquinci.ualabackendchallenge.service.serviceImpl;

import com.pquinci.ualabackendchallenge.dto.CreateUserDTO;
import com.pquinci.ualabackendchallenge.model.User;
import com.pquinci.ualabackendchallenge.repository.UserRepository;
import com.pquinci.ualabackendchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String createUser(CreateUserDTO request) {
        userRepository.save(User.builder().username(request
                        .getUsername())
                .name(request.getName())
                .lastname(request.getLastname())
                .build());

        return "Usuario creado";
    }
}
