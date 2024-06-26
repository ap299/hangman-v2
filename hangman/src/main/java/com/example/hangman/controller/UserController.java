package com.example.hangman.controller;

import com.example.hangman.model.User;

import com.example.hangman.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.register(user);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model,
                            HttpSession session) {
        try {
            User user = userService.login(username, password);
            session.setAttribute("user", user);
            return "redirect:/index";  // Redirect to /index if login succeeds
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";  // Return to login page with error message
        }
    }
    
    @GetMapping("/index")
    public String index() {
        return "index"; // This maps to src/main/resources/templates/index.html
    }
    

}
