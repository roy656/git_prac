package com.sparta.springteamassignment.repository;

import com.sparta.springteamassignment.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}
