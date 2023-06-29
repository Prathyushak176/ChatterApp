package com.chatter.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.chatter.dto.TweetDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "tweet")
public class Tweet {

	@Id
	@GeneratedValue
	private Long id;

	private String content;
	
	private Long userId;
	@Lob
    private byte[] image;

	@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setTweet(this);
    }

    public Tweet(TweetDTO tweetdto) {
        this.id = tweetdto.getId();
        this.content = tweetdto.getContent();
       // this.image = tweetdto.getImage();
        this.userId = tweetdto.getUserId();
    }

    

}
