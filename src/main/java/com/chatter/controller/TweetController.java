

package com.chatter.controller;

import com.chatter.dto.CommentDTO;
import com.chatter.dto.TweetDTO;
import com.chatter.entity.Tweet;
import com.chatter.exception.TweetNotFoundException;
import com.chatter.services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tweet")
@CrossOrigin(origins = "http://localhost:3000")
public class TweetController {

    @Autowired
    private TweetService tweetService;
    
   
    
    @PostMapping("/{tweetId}/comments")
    public ResponseEntity<CommentDTO> addCommentToTweet(@PathVariable Long tweetId, @RequestBody CommentDTO commentdto) throws TweetNotFoundException{
        CommentDTO savedComment = tweetService.addCommentToTweet(tweetId, commentdto);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }
    @PostMapping("/savetweet")
    public ResponseEntity<TweetDTO> save(@ModelAttribute @Valid TweetDTO tweet) {
        TweetDTO savedTweet = tweetService.addTweet(tweet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTweet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable("id") Long id) throws TweetNotFoundException {
        tweetService.deleteTweet(id);
        return ResponseEntity.ok("Tweet with ID " + id + " was deleted successfully");
    }



    @PutMapping("/updatetweet/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable Long id, @RequestBody @Valid TweetDTO tweetdto) throws TweetNotFoundException {
        Tweet updatedTweet = tweetService.updateTweet(id, tweetdto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedTweet);
    }

    @GetMapping("/gettweet/{id}")
    public ResponseEntity<Tweet> getTweet(@PathVariable("id") Long id) throws TweetNotFoundException {
        Tweet tweet = tweetService.getTweetDetails(id);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping("/getalltweets")
    public ResponseEntity<List<Tweet>> getAllTweets() {
        List<Tweet> tweets = tweetService.getAllTweetDetails();
        return ResponseEntity.ok(tweets);
    }
    
    

    @DeleteMapping("/deletealltweets")
    public ResponseEntity<String> deleteAllTweets() {
        tweetService.deleteAll();
        return ResponseEntity.ok("All tweets deleted successfully");
    }
    
}
