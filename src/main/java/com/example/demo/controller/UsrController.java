package com.example.demo.controller;

import com.example.demo.entity.Usr;
import com.example.demo.service.UsrService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsrController {
    private final UsrService usrService;

    public UsrController(UsrService usrService) {
        this.usrService = usrService;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", usrService.findAll());
        model.addAttribute("newUser", new Usr());
        return "users";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("newUser") Usr user) {
        usrService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/update-email")
    public String updateEmail(@RequestParam String username, @RequestParam String newEmail) {
        usrService.updateEmail(username, newEmail);
        return "redirect:/users";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        usrService.deleteUser(username);
        return "redirect:/users";
    }

    @GetMapping("/delete-with-data/{username}")
    public String deleteUserWithData(@PathVariable String username) {
        usrService.deleteUserAndAllData(username);
        return "redirect:/users";
    }
}
