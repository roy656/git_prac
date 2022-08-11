package com.sparta.springteamassignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.springteamassignment.Dto.PostRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "POST")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_Id")
    private Long id;


    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column
    private Long heartCount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference   // 무한 참조 방지
    private List<Comment> commentList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference   // 무한 참조 방지
    private List<Heart> heartList;

    // 유저
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonBackReference
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;


    // Dto 이용한 생성자
    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    public void deleteComment(Comment comment) {
        this.commentList.remove(comment);
    }

    // 게시글 수정
    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }


    //== 연관관계 편의 메서드 ==//

    // User 관련
//    public void confirmUser(User user) {
//        //user는 변경이 불가능하므로 이렇게만 해주어도 될듯
//        this.user = user;
//        user.addPost(this);
//    }

}