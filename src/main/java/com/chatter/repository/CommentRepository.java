package com.chatter.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatter.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByTweetId(Long tweetId);

	Optional<Comment> findByIdAndTweetId(Long commentId, Long tweetId);
}