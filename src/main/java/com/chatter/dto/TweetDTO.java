package com.chatter.dto;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

import com.chatter.entity.Comment;
import com.chatter.entity.Tweet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@ToString
public class TweetDTO {
	private Long id;

	private String content;
	@Lob
    private byte[] image;;
	private Long userId;
	private String userName;
	
	 private List<CommentDTO> comments;

	 public TweetDTO(Tweet tweet) {
	        this.id = tweet.getId();
	        this.content = tweet.getContent();
	        this.image = tweet.getImage();
	        this.userId = tweet.getUserId();
	    }
	
}
