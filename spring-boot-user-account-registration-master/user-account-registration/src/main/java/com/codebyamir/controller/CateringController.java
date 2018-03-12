package com.codebyamir.controller;

import com.codebyamir.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CateringController {
    // Return registration form template
    @RequestMapping(value="/catering", method = RequestMethod.GET)
    public ModelAndView shoCateringPage(ModelAndView modelAndView){
        modelAndView.setViewName("catering");
        return modelAndView;
    }
}
