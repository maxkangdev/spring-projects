package com.springboot.blog.controller;


import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@AllArgsConstructor
@Tag(name = "CRUD REST APIs for Comment Resource")
public class CommentController {

    private CommentService commentService;

    @Operation(
            summary = "Post Comment REST API",
            description = "Insert new comment to post with id into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Insertion Complete"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Comment REST API",
            description = "Get particular comment from the post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get Complete"
    )
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getComentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @Operation(
            summary = "Get All comments REST API",
            description = "Get all comments from the post with id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get Complete"
    )
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable(value = "postId")long postId){
        return new ResponseEntity<>(commentService.getCommentsByPostId(postId),HttpStatus.OK);
    }


    @Operation(
            summary = "Update comment from certain post REST API",
            description = "Update particular comment from the post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get Complete"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId")long postId,
                                                    @PathVariable(value = "commentId")long commentId,
                                                    @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete comment from certain post REST API",
            description = "Delete particular comment from the post"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Delete Complete"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId")long postId,
                                                    @PathVariable(value = "commentId")long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }
}
