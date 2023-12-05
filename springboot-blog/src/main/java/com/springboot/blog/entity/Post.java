package com.springboot.blog.entity;


import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    private static ModelMapper mapper = new ModelMapper();
    public static Post of(PostDto postDto){
        return mapper.map(postDto,Post.class);
    }
}
