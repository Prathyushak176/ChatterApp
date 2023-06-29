package com.chatter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatter.dto.CommentDTO;
import com.chatter.exception.CommentNotFoundException;
import com.chatter.exception.TweetNotFoundException;
import com.chatter.services.CommentService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<CommentDTO> saveComment(@RequestBody CommentDTO commentDTO) throws TweetNotFoundException {
        CommentDTO savedComment = commentService.saveComment(commentDTO);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/tweet/{tweetId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByTweetId(@PathVariable Long tweetId) {
        List<CommentDTO> comments = commentService.getCommentsByTweetId(tweetId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/comments/tweet/{tweetId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long tweetId,
            @PathVariable Long commentId
    ) {
        try {
            commentService.deleteComment(tweetId, commentId);
            return ResponseEntity.ok("Comment deleted successfully");
        } catch (CommentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
