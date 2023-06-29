package com.chatter.dto;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.chatter.entity.Comment;
import com.chatter.entity.Tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

@Getter
@Setter
public class CommentDTO {

	
	private Long tweetId;
	private String content;

	private Long id;

	private Long userId;
	private Tweet tweet;
	

    public CommentDTO(Long id, String content, Long tweetId) {
        this.id = id;
        this.content = content;
        this.tweetId = tweetId;
    }
	public CommentDTO(Comment comment) {
		
		this.content = comment.getContent();
		this.id = comment.getId();
		this.userId = comment.getUserId();
		this.tweet=comment.getTweet();
	}

}
