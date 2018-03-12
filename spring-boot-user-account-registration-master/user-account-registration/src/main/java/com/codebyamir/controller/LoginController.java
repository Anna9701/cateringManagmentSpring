package com.codebyamir.controller;

import com.codebyamir.model.User;
import com.codebyamir.service.EmailService;
import com.codebyamir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public LoginController(BCryptPasswordEncoder bCryptPasswordEncoder,
                              UserService userService, EmailService emailService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    // Return registration form template
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(ModelAndView model) {

        model.addObject("message", "Logged out successfully.");
        model.setViewName("logout");

        return model;
    }
}
