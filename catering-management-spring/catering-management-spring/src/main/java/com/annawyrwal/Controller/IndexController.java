package com.annawyrwal.Controller;

import com.annawyrwal.Email;
import com.annawyrwal.Service.Authentication.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class IndexController {
    @Autowired
    private EmailService emailService;

    @Value("${user.administrator.email}")
    String adminEmail;

    private String IndexName = "Index";

    // Return registration form template
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView showIndexPage(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        modelAndView.addObject("IndexName", IndexName);
        Email email = new Email();
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @RequestMapping(value="/index/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmail(ModelAndView modelAndView,
                                  Email email,
                                  BindingResult bindingResult,
                                  HttpServletRequest request) {
        modelAndView.setViewName("index");
        modelAndView.addObject("IndexName", IndexName);
        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(adminEmail);
        registrationEmail.setSubject("Catering App: Contact form question");
        registrationEmail.setText(email.getEmail() + "\n" + email.getName() + " wrote: \n" + email.getContent());

        emailService.sendEmail(registrationEmail);
        email = new Email();
        modelAndView.addObject("email", email);
        modelAndView.addObject("emailSent", "E-mail was successfully sent");
        return modelAndView;
    }
}
