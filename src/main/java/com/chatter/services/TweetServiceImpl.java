
package com.chatter.services;

import com.chatter.dto.CommentDTO;
import com.chatter.dto.TweetDTO;
import com.chatter.entity.Comment;
import com.chatter.entity.Tweet;

import com.chatter.exception.TweetNotFoundException;
import com.chatter.repository.CommentRepository;
import com.chatter.repository.TweetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	private TweetRepository tweetRepository;
	@Autowired
	CommentRepository commentRepository;

	@Override
	public CommentDTO addCommentToTweet(Long tweetId, CommentDTO commentdto) throws TweetNotFoundException {
		Tweet tweet = tweetRepository.findById(tweetId)
				.orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

		Comment comt = new Comment();
		comt.setContent(commentdto.getContent());
		comt.setTweet(tweet);
		Comment savedComment = commentRepository.save(comt);

		tweet.addComment(savedComment);
		tweetRepository.save(tweet);

		return new CommentDTO(savedComment.getId(), savedComment.getContent(), tweet.getId());
	}
	

	@Override
	public TweetDTO addTweet(TweetDTO tweetdto) {
		Tweet tweet = new Tweet(tweetdto);
		return new TweetDTO(tweetRepository.save(tweet));
	}

	@Override
	public void deleteTweet(Long id) throws TweetNotFoundException {
	    Optional<Tweet> found = tweetRepository.findById(id);
	    if (found.isPresent()) {
	        tweetRepository.delete(found.get());
	    } else {
	        throw new TweetNotFoundException("Tweet not found with id " + id);
	    }
	}


	@Override
	@Transactional
	public Tweet updateTweet(Long id, TweetDTO tweetdto) throws TweetNotFoundException {
		Tweet twt = tweetRepository.findById(id)
				.orElseThrow(() -> new TweetNotFoundException("Id does not exist with given id: " + id));

		twt.setContent(tweetdto.getContent());
//        twt.setImage(tweetdto.getImage());

		return tweetRepository.save(twt);
	}

	
	
	@Override
	public Tweet getTweetDetails(Long tweetId) throws TweetNotFoundException {
	    return tweetRepository.findById(tweetId)
	            .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));
	}


	@Override
	public List<Tweet> getAllTweetDetails() {
		return tweetRepository.findAll();
	}

	@Override
	public void deleteAll() {
		tweetRepository.deleteAll();
	}

	

}
	