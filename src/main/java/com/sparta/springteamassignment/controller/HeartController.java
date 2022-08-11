package com.sparta.springteamassignment.controller;


import com.sparta.springteamassignment.service.HeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartController {

    private final HeartService heartService;

    @Autowired
    public HeartController(HeartService heartService) {
        this.heartService = heartService; }

    @PostMapping("/hearts/{postId}")                    // 반환타입으로 좋아요가 눌렸는지 직관적으로 표시할 방법이 없을까
    public void addPostHeart(@PathVariable Long postId) {
        heartService.addPostHeart(postId);
    }
}
