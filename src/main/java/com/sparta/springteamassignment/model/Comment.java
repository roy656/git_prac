package com.sparta.springteamassignment.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sparta.springteamassignment.Dto.CommentRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.FetchType.LAZY;

@Table(name="comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_Id")
    private Long id;

    // 댓글 작성자
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    // 댓글 내용
    @Column(nullable = false)
    private String content;

    // 댓글이 달리는 게시글
    @ManyToOne(fetch = LAZY)
    @JsonBackReference
    @JoinColumn(name = "post_id")
    private Post post;

    // 첫번째 댓글 (부모댓글)
    @ManyToOne(fetch = LAZY)
    @JsonBackReference
    @JoinColumn(name = "parent_id")
    private Comment parent;

    // 대댓글들 (자식 댓글들)
    @OneToMany(mappedBy = "parent")
    @JsonManagedReference
    private List<Comment> childList = new ArrayList<>();


    //== 연관관계 편의 메서드 ==//

//     User 관련
//    public void confirmWriter(User user) {
//        this.user = user;
//        user.addComment(this);
//    }

    public Comment(Post post, CommentRequestDto requestDto) {
        this.post = post;
        this.content = requestDto.getContent();
    }

    public Comment(Post post, Comment parentComment, CommentRequestDto requestDto) {
        this.post = post;
        this.parent = parentComment;
        this.content = requestDto.getContent();
    }

    // 댓글 수정
    public void updateCmt(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }


}
