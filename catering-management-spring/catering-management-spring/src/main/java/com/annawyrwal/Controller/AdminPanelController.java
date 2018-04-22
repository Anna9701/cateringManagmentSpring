package com.annawyrwal.Controller;

import com.annawyrwal.Service.Authentication.MyUserPrincipal;
import com.annawyrwal.Service.Authentication.UserService;
import com.annawyrwal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AdminPanelController {
    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public ModelAndView showAdminPage(ModelAndView modelAndView, HttpServletRequest request) {
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelAndView.setViewName("adminPanel");

        User user1 = userService.findByUsername(user.getUsername());
        modelAndView.addObject("user", user1);

        return modelAndView;
    }

    @RequestMapping(value = "/adminPanel/updatePassword", method = RequestMethod.POST)
    public ModelAndView updatePassword(ModelAndView modelAndView,
                                                @Valid User user,
                                                BindingResult bindingResult,
                                                HttpServletRequest request) {
        modelAndView.setViewName("/adminPanel");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "Password changed successfully");
        return modelAndView;
    }

    @RequestMapping(value = "/adminPanel/updateEmail", method = RequestMethod.POST)
    public ModelAndView updateEmail(ModelAndView modelAndView,
                                 @Valid User user,
                                 BindingResult bindingResult,
                                 HttpServletRequest request) {
        modelAndView.setViewName("/adminPanel");
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "Email changed successfully");
        return modelAndView;
    }
}

//TODO admin lista klientow
//TODO ikonki w formularzach