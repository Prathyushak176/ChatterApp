
package com.chatter.entity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.chatter.controller.TweetController;
import com.chatter.dto.CommentDTO;
import com.chatter.dto.TweetDTO;
import com.chatter.entity.Comment;
import com.chatter.entity.Tweet;
import com.chatter.exception.TweetNotFoundException;
import com.chatter.services.TweetService;

public class TweetTest {

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private TweetController tweetController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCommentToTweet() throws TweetNotFoundException {
        
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent("Test comment");
        commentDTO.setTweetId(1L);

       
        CommentDTO savedCommentDTO = new CommentDTO();
        savedCommentDTO.setId(1L);
        savedCommentDTO.setContent(commentDTO.getContent());
        savedCommentDTO.setTweetId(1L);

      
        when(tweetService.addCommentToTweet(1L, commentDTO)).thenReturn(savedCommentDTO);

     
        ResponseEntity<CommentDTO> response = tweetController.addCommentToTweet(1L, commentDTO);

   
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedCommentDTO, response.getBody());
    }

    @Test
    public void testSaveTweet() {
    
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setContent("Test tweet");
        tweetDTO.setUserId(1L);

        // Create a mock TweetDTO object
        TweetDTO savedTweetDTO = new TweetDTO();
        savedTweetDTO.setId(1L);
        savedTweetDTO.setContent(tweetDTO.getContent());
        savedTweetDTO.setUserId(1L);

       
        when(tweetService.addTweet(tweetDTO)).thenReturn(savedTweetDTO);

   
        ResponseEntity<TweetDTO> response = tweetController.save(tweetDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedTweetDTO, response.getBody());
    }

    @Test
    public void testDeleteTweet() throws TweetNotFoundException {
     
        doNothing().when(tweetService).deleteTweet(1L);

      
        ResponseEntity<String> response = tweetController.deleteTweet(1L);

  
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tweet with ID 1 was deleted successfully", response.getBody());
    }
    @Test
    public void testUpdateTweet() throws TweetNotFoundException {
        // Create a mock TweetDTO object
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(1L);
        tweetDTO.setContent("Updated tweet");

        // Mock the behavior of the tweet service
        Tweet updatedTweet = new Tweet(tweetDTO);
        when(tweetService.updateTweet(1L, tweetDTO)).thenReturn(updatedTweet);

        // Call the controller method
        ResponseEntity<Tweet> response = tweetController.updateTweet(1L, tweetDTO);

        // Verify the response
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(tweetDTO.getId(), response.getBody().getId());
        assertEquals(tweetDTO.getContent(), response.getBody().getContent());
    }




    @Test
    public void testGetTweet() throws TweetNotFoundException {
      
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(1L);
        tweetDTO.setContent("Test tweet");

        when(tweetService.getTweetDetails(1L)).thenReturn(new Tweet(tweetDTO));

        
        ResponseEntity<Tweet> response = tweetController.getTweet(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tweetDTO.getId(), response.getBody().getId());
        assertEquals(tweetDTO.getContent(), response.getBody().getContent());
    }


    @Test
    public void testGetAllTweets() {
        // Create a list of mock TweetDTO objects
        List<TweetDTO> tweetDTOList = new ArrayList<>();
        TweetDTO tweetDTO1 = new TweetDTO();
        tweetDTO1.setId(1L);
        tweetDTO1.setContent("Test tweet 1");
        tweetDTOList.add(tweetDTO1);

        TweetDTO tweetDTO2 = new TweetDTO();
        tweetDTO2.setId(2L);
        tweetDTO2.setContent("Test tweet 2");
        tweetDTOList.add(tweetDTO2);
        List<Tweet> tweetList = tweetDTOList.stream().map(Tweet::new).collect(Collectors.toList());
        when(tweetService.getAllTweetDetails()).thenReturn(tweetList);

       
        ResponseEntity<List<Tweet>> response = tweetController.getAllTweets();

       
        List<TweetDTO> responseDTOList = response.getBody().stream()
                .map(TweetDTO::new)
                .collect(Collectors.toList());

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tweetDTOList.size(), responseDTOList.size());
        for (int i = 0; i < tweetDTOList.size(); i++) {
            assertEquals(tweetDTOList.get(i).getId(), responseDTOList.get(i).getId());
            assertEquals(tweetDTOList.get(i).getContent(), responseDTOList.get(i).getContent());
        }
    }


    
}
