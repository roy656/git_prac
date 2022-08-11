package com.sparta.springteamassignment.controller;

import com.sparta.springteamassignment.Dto.PostRequestDto;
import com.sparta.springteamassignment.model.Post;
import com.sparta.springteamassignment.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PostController {

    private final PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    // 유저는 아직 전부 적용 안함


    // 게시글 저장
    @PostMapping("/posts")       // 나중에 유저 아이디값을 붙여줘야 함
    public Post creatPost(@RequestBody PostRequestDto requestDto){
        return postService.creatPost(requestDto);
    }


    // 게시글 수정
    @PutMapping("/posts/{postId}")
    public void updatePost(@PathVariable Long postId,
                       @RequestBody PostRequestDto requestDto){
        postService.updatePost(postId, requestDto);
    }


    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public Long deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }


    // 개별 게시글 조회
    @GetMapping("/posts/{postId}")
    public Post getThisPost(@PathVariable Long postId){
        return postService.getThisPost(postId);
    }



    // 전체 게시글 조회
    @GetMapping("/posts")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }
}
