package com.annawyrwal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminPanelController {

    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, HttpServletRequest request) {
      //  MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelAndView.setViewName("adminPanel");

        return modelAndView;
    }
}
