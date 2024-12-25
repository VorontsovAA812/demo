package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.Theme;
import com.example.demo.entity.Usr;
import com.example.demo.service.PostService;
import com.example.demo.service.ThemeService;
import com.example.demo.service.UsrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UsrService usrService;
    private final ThemeService themeService;

    public PostController(PostService postService, UsrService usrService, ThemeService themeService) {
        this.postService = postService;
        this.usrService = usrService;
        this.themeService = themeService;
    }

    @GetMapping
    public String listAll(Model model, HttpSession session) {
        String currentUser = (String) session.getAttribute("username");
        String selectedTheme = (String) session.getAttribute("selectedTheme");

        if (currentUser == null || selectedTheme == null) {
            return "redirect:/";
        }

        model.addAttribute("posts", postService.findByThemeName(selectedTheme));
        model.addAttribute("currentTheme", selectedTheme);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newPost", new Post());

        return "posts";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute("newPost") Post post, HttpSession session) {
        String currentUser = (String) session.getAttribute("username");
        String selectedTheme = (String) session.getAttribute("selectedTheme");

        if (currentUser == null || selectedTheme == null) {
            return "redirect:/";
        }

        Usr user = usrService.findById(currentUser);
        Theme theme = themeService.findById(selectedTheme);
        if (user == null || theme == null) {
            return "redirect:/";
        }

        post.setUser(user);
        post.setTheme(theme);
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/{postId}/comments")
    public String showComments(@PathVariable Long postId) {
        return "redirect:/comments?post=" + postId;
    }

    @PostMapping("/update-post")
    public String updatePostContent(
            @RequestParam Long postId,
            @RequestParam String newTheme,
            @RequestParam String newContent
    ) {
        postService.updatePost(postId, newTheme, newContent);
        return "redirect:/posts";
    }
}
