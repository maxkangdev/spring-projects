package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;

    public Comment toEntity(){
        return Comment.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .body(this.body)
                .build();
    }

}
