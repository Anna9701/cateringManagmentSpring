package com.annawyrwal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorsController {

    @RequestMapping(value="/error/403", method = RequestMethod.GET)
    public ModelAndView show403Page(ModelAndView modelAndView){
        modelAndView.setViewName("error/403");
        return modelAndView;
    }

    @RequestMapping(value="/error/404", method = RequestMethod.GET)
    public ModelAndView show404Page(ModelAndView modelAndView){
        modelAndView.setViewName("error/404");
        return modelAndView;
    }

    @RequestMapping(value="/error/400", method = RequestMethod.GET)
    public ModelAndView show400Page(ModelAndView modelAndView){
        modelAndView.setViewName("error/400");
        return modelAndView;
    }

    @RequestMapping(value="/error/405", method = RequestMethod.GET)
    public ModelAndView show405Page(ModelAndView modelAndView){
        modelAndView.setViewName("error/405");
        return modelAndView;
    }

    @RequestMapping(value="/error/500", method = RequestMethod.GET)
    public ModelAndView show500Page(ModelAndView modelAndView){
        modelAndView.setViewName("error/500");
        return modelAndView;
    }
}
