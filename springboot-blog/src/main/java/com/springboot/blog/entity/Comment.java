package com.springboot.blog.entity;


import com.springboot.blog.payload.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    public CommentDto toDto(){
        return CommentDto.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .body(this.body)
                .build();
    }
}
