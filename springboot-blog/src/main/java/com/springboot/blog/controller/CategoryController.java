package com.springboot.blog.controller;


import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@Tag(name = "CRUD REST APIs for Category Resource")
public class CategoryController {

    private CategoryService categoryService;

    @Operation(
            summary = "Post Category REST API",
            description = "Insert new category into the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Insertion Complete"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get Category REST API",
            description = "Get category with ID from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get Complete"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(value = "id") Long categoryId){
        CategoryDto category = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Categories REST API",
            description = "Get categories from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get Complete"
    )
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @Operation(
            summary = "Update Category REST API",
            description = "Update category with ID from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Update Complete"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable(value = "id") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
    }

    @Operation(
            summary = "Delete Category REST API",
            description = "Delete category with ID from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Delete Complete"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable(value = "id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category successfully deleted");
    }
}
