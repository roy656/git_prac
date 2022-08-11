package com.sparta.springteamassignment.service;


import com.sparta.springteamassignment.Dto.UserRequestDto;
import com.sparta.springteamassignment.model.User;
import com.sparta.springteamassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // 회원가입
    public User resiterUser(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 있습니다");
        }

        return new User(username, password);
    }
}
