package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private ModelMapper modelMapper;
    private PostRepository postRepository;
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));

        Post post = Post.of(postDto);
        post.setCategory(category);

        Post newPost = postRepository.save(post);
        return PostDto.of(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageRequest);
        List<Post> postList = posts.getContent();

        List<PostDto> postDtoList = postList.stream()
                .map(PostDto::of)
                .collect(Collectors.toList());

        return PostResponse.builder()
                .content(postDtoList)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(posts.getTotalElements())
                .totalPages(posts.getTotalPages())
                .last(posts.isLast())
                .build();
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return PostDto.of(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Category category = categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(post.getDescription());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);
        return PostDto.of(updatedPost);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        return postRepository.findByCategoryId(categoryId).stream().map(post -> PostDto.of(post)).collect(Collectors.toList());
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}
