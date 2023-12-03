package com.springboot.blog.payload;

import com.springboot.blog.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;

    private static ModelMapper mapper = new ModelMapper();
    public static CommentDto of(Comment comment){
        return mapper.map(comment,CommentDto.class);
    }
}