package com.example.demo.controller;

import com.example.demo.UserForm;
import com.example.demo.service.ThemeService;
import com.example.demo.service.UsrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final ThemeService themeService;
    private final UsrService usrService;

    public HomeController(ThemeService themeService, UsrService usrService) {
        this.themeService = themeService;
        this.usrService = usrService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("themes", themeService.findAll());
        return "index";
    }

    @PostMapping("/set-context")
    public String setContext(@ModelAttribute("userForm") UserForm userForm,
                             @RequestParam String themeName,
                             HttpSession session) {
        session.setAttribute("username", userForm.getUsername());
        session.setAttribute("selectedTheme", themeName);

        usrService.createDefaultUser(userForm.getUsername());
        themeService.createDefaultTheme(themeName, userForm.getUsername());

        return "redirect:/posts";
    }
}
