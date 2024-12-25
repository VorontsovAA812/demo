package com.example.demo.controller;

import com.example.demo.entity.Theme;
import com.example.demo.entity.Usr;
import com.example.demo.service.ThemeService;
import com.example.demo.service.UsrService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/themes")
public class ThemeController {
    private final ThemeService themeService;
    private final UsrService usrService;

    public ThemeController(ThemeService themeService, UsrService usrService) {
        this.themeService = themeService;
        this.usrService = usrService;
    }

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("themes", themeService.findAll());
        model.addAttribute("newTheme", new Theme());
        model.addAttribute("users", usrService.findAll());
        return "themes";
    }

    @PostMapping("/save")
    public String saveTheme(@ModelAttribute("newTheme") Theme theme,
                            @RequestParam(required = false) String creatorUsername) {
        if (creatorUsername != null && !creatorUsername.isBlank()) {
            if (usrService.findById(creatorUsername) == null) {
                usrService.createDefaultUser(creatorUsername);
            }
            Usr creator = usrService.findById(creatorUsername);
            theme.setCreator(creator);
        }
        themeService.save(theme);
        return "redirect:/themes";
    }

    @GetMapping("/delete/{themeName}")
    public String deleteTheme(@PathVariable String themeName) {
        themeService.deleteById(themeName);
        return "redirect:/themes";
    }

    @GetMapping("/delete-with-posts/{themeName}")
    public String deleteThemeWithPosts(@PathVariable String themeName) {
        themeService.deleteThemeAndAllPosts(themeName);
        return "redirect:/themes";
    }
}
