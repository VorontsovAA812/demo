package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.service.CommentService;
import com.example.demo.service.PostService;
import com.example.demo.service.UsrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    private final UsrService usrService;

    public CommentController(CommentService commentService, PostService postService, UsrService usrService) {
        this.commentService = commentService;
        this.postService = postService;
        this.usrService = usrService;
    }

    @GetMapping
    public String listComments(@RequestParam("post") Long postId, Model model, HttpSession session) {
        Post post = postService.findById(postId);
        model.addAttribute("comments", commentService.findByPostId(postId));
        model.addAttribute("newComment", new Comment());
        model.addAttribute("post", post);
        String currentUser = (String) session.getAttribute("username");
        model.addAttribute("currentUser", currentUser);
        return "comments";
    }

    @PostMapping("/save")
    public String saveComment(@ModelAttribute("newComment") Comment comment,
                              @RequestParam("postId") Long postId,
                              HttpSession session) {
        String currentUser = (String) session.getAttribute("username");
        commentService.insertComment(comment.getContent(), currentUser, postId);
        return "redirect:/comments?post=" + postId;
    }
}
