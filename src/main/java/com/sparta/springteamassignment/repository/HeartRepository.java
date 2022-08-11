package com.sparta.springteamassignment.repository;

import com.sparta.springteamassignment.model.Comment;
import com.sparta.springteamassignment.model.Heart;
import com.sparta.springteamassignment.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByPost(Post post);

    public Long countByPost(Post post);
}
