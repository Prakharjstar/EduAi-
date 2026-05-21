package com.gpt5.gpt5.Controller;

import com.gpt5.gpt5.Repository.UserRepository;
import com.gpt5.gpt5.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {

        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user,
                         Model model) {

        User existingUser = (User) userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {

            model.addAttribute("error", "Email Already Exists");

            return "signup";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        User user = (User) userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            model.addAttribute("name", user.getName());

            return "ChatPage";
        }

        model.addAttribute("error", "Invalid Email or Password");

        return "login";
    }
}