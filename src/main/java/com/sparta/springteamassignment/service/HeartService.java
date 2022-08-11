package com.sparta.springteamassignment.service;


import com.sparta.springteamassignment.model.Comment;
import com.sparta.springteamassignment.model.Heart;
import com.sparta.springteamassignment.model.Post;
import com.sparta.springteamassignment.repository.HeartRepository;
import com.sparta.springteamassignment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final PostRepository postRepository;

    @Autowired
    public HeartService(HeartRepository heartRepository, PostRepository postRepository) {
        this.heartRepository = heartRepository;
        this.postRepository = postRepository;
    }

    // 해당 Post 에 좋아요가 이미 있는지 확인하기 위한 메소드 생성
    public boolean isThereArePost(Post post) {
        return heartRepository.findByPost(post).isPresent();
    }

    public void addPostHeart(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다"));
        Heart heart = new Heart(post);

        if(isThereArePost(post)) {      // 해당 게시글에 좋아요가 있다면 찾아와서 삭제하고
            Heart heart1 = heartRepository.findByPost(post).get();
            heartRepository.delete(heart1);
        } else {
            Heart heart2 = new Heart(post);     // 없다면 새로 좋아요 생성
            heartRepository.save(heart2);
        }
    }

}
