package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.Theme;
import com.example.demo.entity.Usr;
import com.example.demo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UsrService usrService;
    private final ThemeService themeService;

    public PostService(PostRepository postRepository, UsrService usrService, ThemeService themeService) {
        this.postRepository = postRepository;
        this.usrService = usrService;
        this.themeService = themeService;
    }


    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findByThemeName(String themeName) {
        return postRepository.findByThemeName(themeName);
    }

    public void updatePost(Long postId, String newTheme, String newContent) {
        postRepository.updatePostThemeAndContent(postId, newTheme, newContent);
    }

    public void deletePostsByUser(String username) {
        postRepository.deletePostsByUsername(username);
    }

    public void deletePostsByTheme(String themeName) {
        postRepository.deletePostsByTheme(themeName);
    }
}
