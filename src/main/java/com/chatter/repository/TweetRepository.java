package com.chatter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatter.dto.TweetDTO;
import com.chatter.entity.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long>{

}

