package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value="SELECT * FROM post WHERE theme_name = :tname", nativeQuery=true)
    List<Post> findByThemeName(@Param("tname") String themeName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE post SET theme_name = :newTheme, content = :newContent WHERE post_id = :pid", nativeQuery = true)
    void updatePostThemeAndContent(@Param("pid") Long postId, @Param("newTheme") String newTheme, @Param("newContent") String newContent);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM post WHERE username = :uname", nativeQuery = true)
    void deletePostsByUsername(@Param("uname") String username);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM post WHERE theme_name = :tname", nativeQuery = true)
    void deletePostsByTheme(@Param("tname") String themeName);
}
