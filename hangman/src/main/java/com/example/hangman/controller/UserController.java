package com.example.hangman.controller;

import com.example.hangman.model.User;
import com.example.hangman.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String registerUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (userService.userExists(user.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "Username already exists");
            return "register";
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(user);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) boolean error, Model model) {
        if (error) {
            model.addAttribute("error", "Invalid username or password");
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            User loggedInUser = userService.login(user.getUsername(), user.getPassword());
            session.setAttribute("user", loggedInUser);
            return "redirect:/index";  // Redirect to /index if login succeeds
        } catch (Exception e) {
            return "redirect:/users/login?error=true";  // Redirect to login page with error
        }
    }

    @GetMapping("/index")
    public String index() {
        return "index"; // This maps to src/main/resources/templates/index.html
    }
}