package com.sparta.springteamassignment.Dto;

import com.sparta.springteamassignment.model.Comment;
import com.sparta.springteamassignment.model.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class PostRequestDto {

    private String title;
    private String content;
}

