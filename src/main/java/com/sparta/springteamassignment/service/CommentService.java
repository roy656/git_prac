package com.sparta.springteamassignment.service;

import com.sparta.springteamassignment.Dto.CommentRequestDto;
import com.sparta.springteamassignment.model.Comment;
import com.sparta.springteamassignment.model.Post;
import com.sparta.springteamassignment.repository.CommentRepository;
import com.sparta.springteamassignment.repository.PostRepository;
import com.sparta.springteamassignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    // 댓글 작성
    @Transactional
    public Comment addComment(Long postId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("존재하는 아이디가 없습니다"));
        Comment comment = new Comment(post, requestDto);
        commentRepository.save(comment);
        post.addComment(comment);
        return comment;
    }


    // 대댓글 작성
    @Transactional
    public Comment addReComment(Long postId, Long commentId, CommentRequestDto requestDto) {
        Post foundPost = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("아이디를 찾을 수 없습니다"));
        Comment parentComment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다"));

        Comment reComment = new Comment(foundPost, parentComment, requestDto);
        return commentRepository.save(reComment);
    }


    // 댓글 수정
    @Transactional
    public Comment updateComment(Long postId, Long commentId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다"));
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다"));

        comment.updateCmt(requestDto);
        return comment;
    }


    // 댓글 삭제
    @Transactional
    public void deleteComment(Long postId, Long commentId) {        // remove 타입 boolean? 반환타입 boolean?
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("게시글을 찾을 수 없습니다"));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NullPointerException("댓글을 찾을 수 없습니다"));
        post.deleteComment(comment);

    }
}
