package com.springboot.blog.payload;


import com.springboot.blog.entity.Post;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

    private static ModelMapper mapper = new ModelMapper();
    public static PostDto of(Post post){
        return mapper.map(post,PostDto.class);
    }
}
