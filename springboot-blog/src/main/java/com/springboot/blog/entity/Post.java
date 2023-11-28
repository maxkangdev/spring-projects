package com.springboot.blog.entity;


import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.springboot.blog.payload.PostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
@Builder
public class Post {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    public PostDto toDto(){
        return PostDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .content(this.content)
                .build();
    }
}
