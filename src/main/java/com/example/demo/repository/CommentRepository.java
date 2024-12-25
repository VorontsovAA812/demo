package com.example.demo.repository;

import com.example.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO comment (content, username, post_id) VALUES (:content, :username, :postId)", nativeQuery = true)
    void insertComment(@Param("content") String content,
                       @Param("username") String username,
                       @Param("postId") Long postId);

    @Query(value="SELECT * FROM comment WHERE post_id = :pid", nativeQuery=true)
    List<Comment> findByPostId(@Param("pid") Long postId);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM comment WHERE username = :uname", nativeQuery = true)
    void deleteCommentsByUsername(@Param("uname") String username);
}
