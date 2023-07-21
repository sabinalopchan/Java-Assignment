package com.example.java_application.controller;

import com.example.java_application.entity.User;
import com.example.java_application.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home(){
        return "register";
    }

    @GetMapping("/login")
    public String login(){
        return  "login";
    }



    @PostMapping("/register")
    public String registser(@ModelAttribute User user, HttpSession session){


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        System.out.println(user);
        userRepo.save(user);


        session.setAttribute("message", "User Registered Successfully");
        return  "redirect:/";
    }

}
