package com.sparta.springteamassignment.repository;

import com.sparta.springteamassignment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteById(Comment comment);


}
