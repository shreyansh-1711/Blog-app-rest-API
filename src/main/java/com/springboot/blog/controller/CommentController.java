package com.springboot.blog.controller;


import com.springboot.blog.entity.Comment;
import com.springboot.blog.payload.ApiResponse;
import com.springboot.blog.payload.CommentDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<CommentDto>> createComment(@PathVariable(value = "postId") long postId,
                                                                 @RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(postId, commentDto);
        ApiResponse<CommentDto> response = new ApiResponse<>("Comment created successfully", createdComment, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<ApiResponse<List<CommentDto>>> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        ApiResponse<List<CommentDto>> response = new ApiResponse<>("Comments retrieved successfully", comments, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentDto>> getCommentById( @PathVariable(value = "postId") long postId,  @PathVariable(value = "commentId") long commentId) {

        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        ApiResponse<CommentDto> response = new ApiResponse<>("Comments fetched successfully", commentDto, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<CommentDto>> updateComment(@PathVariable(value = "postId") long postId,
                                                                 @PathVariable(value = "commentId") long commentId,
                                                                 @RequestBody CommentDto commentDto) {

        CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
        ApiResponse<CommentDto> response =  new ApiResponse<>("Comment updated successfully", updatedComment, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable(value = "postId") long postId,  @PathVariable(value = "commentId") long commentId) {

        commentService.deleteCommentById(postId, commentId);
        ApiResponse<Void> response = new ApiResponse<>("Post deleted successfully", null, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
