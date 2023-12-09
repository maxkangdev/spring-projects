package com.springboot.blog.entity;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.CommentDto;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.dynamic.loading.InjectionClassLoader;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    private static ModelMapper mapper = new ModelMapper();

    public static Category of(CategoryDto categoryDto){
        return mapper.map(categoryDto,Category.class);
    }

}
