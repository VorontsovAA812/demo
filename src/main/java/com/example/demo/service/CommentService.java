package com.example.demo.service;

import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public void insertComment(String content, String username, Long postId) {
        commentRepository.insertComment(content, username, postId);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteCommentsByUser(String username) {
        commentRepository.deleteCommentsByUsername(username);
    }
}
