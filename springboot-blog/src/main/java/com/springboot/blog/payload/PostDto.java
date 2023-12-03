package com.springboot.blog.payload;


import com.springboot.blog.entity.Post;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    private static ModelMapper mapper = new ModelMapper();
    public static PostDto of(Post post){
        return mapper.map(post,PostDto.class);
    }
}
