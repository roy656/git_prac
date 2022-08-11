package com.sparta.springteamassignment.controller;


import com.sparta.springteamassignment.Dto.UserRequestDto;
import com.sparta.springteamassignment.model.User;
import com.sparta.springteamassignment.repository.UserRepository;
import com.sparta.springteamassignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 회원가입 API
    @PostMapping("/users")
    public User resiterUser(UserRequestDto requestDto) {
        return userService.resiterUser(requestDto);
    }

    @GetMapping("/users")
    public List<User> getAllusers() {
       List<User> userList = userRepository.findAll();
       return userList;
    }
}
