package com.springboot.blog.payload;

import com.springboot.blog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String description;

    private static ModelMapper mapper = new ModelMapper();
    public static CategoryDto of(Category category){
        return mapper.map(category,CategoryDto.class);
    }
}
