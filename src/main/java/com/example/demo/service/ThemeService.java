package com.example.demo.service;

import com.example.demo.entity.Theme;
import com.example.demo.entity.Usr;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.ThemeRepository;
import com.example.demo.repository.UsrRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final UsrRepository usrRepository;
    private final PostRepository postRepository;

    public ThemeService(ThemeRepository themeRepository, UsrRepository usrRepository, PostRepository postRepository) {
        this.themeRepository = themeRepository;
        this.usrRepository = usrRepository;
        this.postRepository = postRepository;
    }

    public List<Theme> findAll() {
        return themeRepository.findAll();
    }

    public Theme findById(String themeName) {
        return themeRepository.findById(themeName).orElse(null);
    }

    public Theme save(Theme theme) {
        return themeRepository.save(theme);
    }

    public void deleteById(String themeName) {
        themeRepository.deleteThemeByName(themeName);
    }

    public void createDefaultTheme(String themeName, String creatorUsername) {
        if (findById(themeName) == null) {
            // Создадим пользователя, если его нет
            if (usrRepository.findById(creatorUsername).isEmpty()) {
                Usr newUser = new Usr(creatorUsername, creatorUsername+"@example.com", "password", null);
                usrRepository.save(newUser);
            }
            Usr creator = usrRepository.findById(creatorUsername).orElse(null);
            Theme theme = new Theme(themeName, null, creator);
            themeRepository.save(theme);
        }
    }

    // Удаление темы и всех её постов
    public void deleteThemeAndAllPosts(String themeName) {
        postRepository.deletePostsByTheme(themeName);
        themeRepository.deleteThemeByName(themeName);
    }
}
