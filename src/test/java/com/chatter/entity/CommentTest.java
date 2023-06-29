package com.chatter.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.chatter.controller.CommentController;
import com.chatter.dto.CommentDTO;
import com.chatter.exception.CommentNotFoundException;
import com.chatter.exception.TweetNotFoundException;
import com.chatter.services.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CommentTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveComment() throws TweetNotFoundException {
        // Create a test comment DTO
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent("Test comment");
        commentDTO.setTweetId(1L);

        // Create a mock CommentDTO object
        CommentDTO savedCommentDTO = new CommentDTO();
        savedCommentDTO.setId(1L);
        savedCommentDTO.setContent(commentDTO.getContent());
        savedCommentDTO.setTweetId(commentDTO.getTweetId());

     
        when(commentService.saveComment(commentDTO)).thenReturn(savedCommentDTO);

    
        ResponseEntity<CommentDTO> response = commentController.saveComment(commentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedCommentDTO, response.getBody());
    }

    @Test
    public void testGetCommentsByTweetId() {
       
        List<CommentDTO> commentDTOList = new ArrayList<>();
        CommentDTO commentDTO1 = new CommentDTO();
        commentDTO1.setId(1L);
        commentDTO1.setContent("Test comment 1");
        commentDTOList.add(commentDTO1);

        CommentDTO commentDTO2 = new CommentDTO();
        commentDTO2.setId(2L);
        commentDTO2.setContent("Test comment 2");
        commentDTOList.add(commentDTO2);

       
        when(commentService.getCommentsByTweetId(1L)).thenReturn(commentDTOList);

        ResponseEntity<List<CommentDTO>> response = commentController.getCommentsByTweetId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTOList.size(), response.getBody().size());
        for (int i = 0; i < commentDTOList.size(); i++) {
            assertEquals(commentDTOList.get(i).getId(), response.getBody().get(i).getId());
            assertEquals(commentDTOList.get(i).getContent(), response.getBody().get(i).getContent());
        }
    }

    @Test
    public void testDeleteComment() throws CommentNotFoundException {

        doNothing().when(commentService).deleteComment(1L, 1L);

      
        ResponseEntity<String> response = commentController.deleteComment(1L, 1L);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment deleted successfully", response.getBody());
    }

    @Test
    public void testDeleteComment_CommentNotFoundException() throws CommentNotFoundException {

        doThrow(CommentNotFoundException.class).when(commentService).deleteComment(1L, 1L);

   
        ResponseEntity<String> response = commentController.deleteComment(1L, 1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }





}
