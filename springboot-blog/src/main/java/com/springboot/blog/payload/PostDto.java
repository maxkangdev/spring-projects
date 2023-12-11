package com.springboot.blog.payload;


import com.springboot.blog.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Set;

@Schema(
        description = "PostDto Model Information"
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;

    @Schema(description = "Blog Post Title")
    @NotEmpty
    @Size(min= 2, message = "Post title should have at least 2 characters")
    private String title;

    @Schema(description = "Blog Post Description")
    @NotNull
    @NotEmpty
    @Size(min= 10, message = "Post description should have at least 10 characters")
    private String description;

    @Schema(description = "Blog Post Content")
    @NotEmpty
    @NotNull
    private String content;
    private Set<CommentDto> comments;

    @Schema(description = "Blog Post Category")
    private Long categoryId;

    private static ModelMapper mapper = new ModelMapper();
    public static PostDto of(Post post){
        return mapper.map(post,PostDto.class);
    }
}
