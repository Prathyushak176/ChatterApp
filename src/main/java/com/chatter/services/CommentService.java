package com.chatter.services;


import java.util.List;

import javax.validation.Valid;

import com.chatter.dto.CommentDTO;
import com.chatter.dto.UserDTO;
import com.chatter.entity.Comment;
import com.chatter.entity.User;
import com.chatter.exception.CommentNotFoundException;
import com.chatter.exception.TweetNotFoundException;

public interface CommentService {

	CommentDTO saveComment(CommentDTO commentDTO)throws TweetNotFoundException;

	List<CommentDTO> getCommentsByTweetId(Long tweetId);

	void deleteComment(Long tweetId, Long commentId)throws CommentNotFoundException;

	
    
	
	

	
}