package com.example.Security_project.controllers;

import com.example.Security_project.models.User;
import com.example.Security_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-in")
    public String signInPage(){
        return "sign-in";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-up")
    public String signUpPage(){
        return "sign-up";
    }
    @PreAuthorize("isAnonymous()")
    @PostMapping("/sign-up")
    public String signUp(User user, @RequestParam String rePassword){
        String result=userService.addUser(user,rePassword);
        return "redirect:/"+result;
    }
}
