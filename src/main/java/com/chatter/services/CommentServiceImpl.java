package com.chatter.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatter.dto.CommentDTO;
import com.chatter.entity.Comment;
import com.chatter.entity.Tweet;
import com.chatter.exception.CommentNotFoundException;
import com.chatter.exception.TweetNotFoundException;
import com.chatter.repository.CommentRepository;
import com.chatter.repository.TweetRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) throws TweetNotFoundException {
        Tweet tweet = tweetRepository.findById(commentDTO.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException("Tweet not found"));

        Comment comment = new Comment(commentDTO);
        comment.setTweet(tweet);

        Comment savedComment = commentRepository.save(comment);
        return new CommentDTO(savedComment);
    }

    @Override
    public List<CommentDTO> getCommentsByTweetId(Long tweetId) {
        List<Comment> comments = commentRepository.findByTweetId(tweetId);
        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }
@Override
    public void deleteComment(Long tweetId, Long commentId) throws CommentNotFoundException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent() && comment.get().getTweet().getId().equals(tweetId)) {
            commentRepository.delete(comment.get());
        } else {
            throw new CommentNotFoundException("Comment not found with id: " + commentId);
        }
    }

}
