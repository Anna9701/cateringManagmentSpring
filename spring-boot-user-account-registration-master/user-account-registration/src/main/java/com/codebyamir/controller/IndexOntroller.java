package com.codebyamir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexOntroller {
    private String IndexName = "Index";

    // Return registration form template
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView showIndexPage(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        modelAndView.addObject("IndexName", IndexName);
        return modelAndView;
    }
}
