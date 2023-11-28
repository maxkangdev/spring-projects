package com.springboot.blog.payload;


import com.springboot.blog.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    public Post toEntity(){
        return Post.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .content(this.content)
                .build();
    }

}
