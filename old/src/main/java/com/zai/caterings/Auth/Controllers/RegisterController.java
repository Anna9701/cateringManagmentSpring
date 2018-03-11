package com.zai.caterings.Auth.Controllers;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.nulabinc.zxcvbn.Strength;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zai.caterings.Auth.Service.EmailService;
import com.zai.caterings.Auth.Service.UserService;
import com.zai.caterings.Models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService, EmailService emailService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    //Return registration from template
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UsersEntity user) {
        modelAndView.addObject("user", "user");
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    //Process from input data
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid UsersEntity user, BindingResult bindingResult, HttpServletRequest request) {
        UsersEntity userExist = userService.findByEmail(user.getEmail());

        if (userExist != null) {
            modelAndView.addObject("alreadyRegisteredMessage", "Oops! There is already user with that e-mail");
            modelAndView.setViewName("registration");
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            user.setEnabled(false);
            user.setConfirmationToken(UUID.randomUUID().toString());
            userService.saveUser(user);
            String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click link below:\n" +
                    appUrl + "/confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("noreply@domain.com");

            emailService.sendEmail(registrationEmail);

            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
            modelAndView.setViewName("registration");
        }

        return modelAndView;
    }

    //Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token ) {
        UsersEntity user = userService.findByConfirmationToken(token);
        if (user == null) {
            modelAndView.addObject("invalidToken", "Oops! This is invalid confirmation link.");
        } else {
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

    //Process confirmation link
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir ) {
        modelAndView.setViewName("confirm");
        Zxcvbn passwordCheck = new Zxcvbn();
        Strength strength = passwordCheck.measure((String) requestParams.get("password"));

        if (strength.getScore() < 3) {
            bindingResult.reject("password");
            redir.addFlashAttribute("errorMessage", "Your password is to week. Choose a stronger one");
            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
            System.out.println(requestParams.get("token"));
            return modelAndView;
        }

        UsersEntity user = userService.findByConfirmationToken((String) requestParams.get("token"));
        user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));
        user.setEnabled(true);
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "Your password has been set!");

        return modelAndView;
    }
}
