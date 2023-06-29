

package com.chatter.services;

import com.chatter.dto.CommentDTO;
import com.chatter.dto.TweetDTO;
import com.chatter.entity.Tweet;
import com.chatter.exception.TweetNotFoundException;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

public interface TweetService {
    TweetDTO addTweet(TweetDTO tweetdto);

 

    Tweet updateTweet(Long id, TweetDTO tweetdto) throws TweetNotFoundException;

    Tweet getTweetDetails(Long id) throws TweetNotFoundException;

    List<Tweet> getAllTweetDetails();

    void deleteAll();

	CommentDTO addCommentToTweet(Long id, CommentDTO commentDTO) throws TweetNotFoundException;

	void deleteTweet(Long id)throws TweetNotFoundException;




	

}
