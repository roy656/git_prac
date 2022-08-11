package com.sparta.springteamassignment.service;


import com.sparta.springteamassignment.Dto.PostRequestDto;
import com.sparta.springteamassignment.model.Comment;
import com.sparta.springteamassignment.model.Heart;
import com.sparta.springteamassignment.model.Post;
import com.sparta.springteamassignment.repository.HeartRepository;
import com.sparta.springteamassignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;

    @Autowired
    public PostService(PostRepository postRepository, HeartRepository heartRepository) {
        this.postRepository = postRepository;
        this.heartRepository = heartRepository;
    }

    // 게시글 작성
    public Post creatPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, PostRequestDto requestDto) {
        Post found = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다"));
        found.update(requestDto);
    }

    // 게시글 삭제
    @Transactional
    public Long deletePost(Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }

    // 개별 게시글 조회
    public Post getThisPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(        // 어떤 게시글을 조회하는지 찾아온 다음
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다"));

        post.setHeartCount(heartRepository.countByPost(post));      // 좋아요 repository 에서 해당 메모로 카운트 해와서 set 해준다

        return post;
    }

    // 전체 게시글 조회
    public List<Post> getAllPost() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }

}



