package com.sparta.springteamassignment.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @JoinColumn(name = "comment_Id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;


    public Heart(Post post) {
        this.post = post;
    }

}
