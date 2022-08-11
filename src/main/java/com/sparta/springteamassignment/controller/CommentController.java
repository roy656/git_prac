package com.sparta.springteamassignment.controller;

import com.sparta.springteamassignment.Dto.CommentRequestDto;
import com.sparta.springteamassignment.model.Comment;
import com.sparta.springteamassignment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {


    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    // 댓글 작성 POST API
    @PostMapping("/comments/{postId}")
    public Comment addComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto){
        return commentService.addComment(postId, requestDto);
    }

    // 대댓글 작성 POST API
    @PostMapping("/comments/{postId}/{commentId}")
    public Comment addReComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @RequestBody CommentRequestDto requestDto){
        return commentService.addReComment(postId, commentId, requestDto);
    }


    // 댓글 수정 PUT API
    @PutMapping("/comments/{postId}/{commentId}")
    public void updateComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              @RequestBody CommentRequestDto requestDto){
        commentService.updateComment(postId, commentId, requestDto);
    }

    // 댓글 삭제 DELETE API
    @DeleteMapping("/comments/{postId}/{commentId}")
    public void delete(@PathVariable Long postId,
                       @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
    }
}
