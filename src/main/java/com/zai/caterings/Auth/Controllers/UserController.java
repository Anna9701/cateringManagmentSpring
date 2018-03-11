package com.zai.caterings.Auth.Controllers;

import com.zai.caterings.Auth.Interfaces.SecurityService;
import com.zai.caterings.Auth.Validator.UserValidator;
import com.zai.caterings.Models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration.html", method = RequestMethod.GET)
    public String registration (Model model) {
        model.addAttribute("userForm", new UsersEntity());

        return "registration.html";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") UsersEntity userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration.html";
        }

        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/index";
    }

    @RequestMapping("/login.html")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid");
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully");

        return "login.html";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}
