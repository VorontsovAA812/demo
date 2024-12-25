package com.example.demo.service;

import com.example.demo.entity.Usr;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UsrRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsrService {
    private final UsrRepository usrRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public UsrService(UsrRepository usrRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.usrRepository = usrRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<Usr> findAll() {
        return usrRepository.findAll();
    }

    public Usr findById(String username) {
        return usrRepository.findById(username).orElse(null);
    }

    public Usr save(Usr user) {
        return usrRepository.save(user);
    }

    public void updateEmail(String username, String newEmail) {
        usrRepository.updateUserEmail(username, newEmail);
    }

    public void deleteUser(String username) {
        usrRepository.deleteUserByUsername(username);
    }

    public void createDefaultUser(String username) {
        if (findById(username) == null) {
            Usr newUser = new Usr(username, username+"@example.com", "password", null);
            usrRepository.save(newUser);
        }
    }

    // Удаление пользователя и всех его данных
    public void deleteUserAndAllData(String username) {
        commentRepository.deleteCommentsByUsername(username);
        postRepository.deletePostsByUsername(username);
        usrRepository.deleteUserByUsername(username);
    }
}
