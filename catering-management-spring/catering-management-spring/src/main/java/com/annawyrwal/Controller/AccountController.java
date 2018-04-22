package com.annawyrwal.Controller;

import com.annawyrwal.Service.Authentication.UserService;
import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.Service.Interfaces.ContactDataService;
import com.annawyrwal.Service.Authentication.MyUserPrincipal;
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
public class AccountController {
    private ClientEntityService clientEntityService;
    private ContactDataService contactDataService;

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountController(ClientEntityService clientEntityService, ContactDataService contactDataService) {
        this.clientEntityService = clientEntityService;
        this.contactDataService = contactDataService;
    }

    // Return account details form template
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, HttpServletRequest request) {
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientsEntity clientsEntity = clientEntityService.findByUser(user.getUser());
        ContactDataEntity contactDataEntity = contactDataService.findByClient(clientsEntity);

        modelAndView.addObject("clientsEntity", clientsEntity);
        modelAndView.addObject("contactDataEntity", contactDataEntity);
        modelAndView.setViewName("account");

        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView,
                                                @Valid ClientsEntity clientsEntity,
                                                @Valid ContactDataEntity contactDataEntity,
                                                BindingResult bindingResult,
                                                HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("account");
            return modelAndView;
        }

        contactDataEntity.setClientsByClientid(clientsEntity);
        clientEntityService.updateClient(clientsEntity);
        contactDataService.updateContactData(contactDataEntity);
        modelAndView.addObject("successMessage", "Your account was successfully updated.");

        return modelAndView;
    }



    @RequestMapping(value = "/account/changePassword", method = RequestMethod.GET)
    public ModelAndView showAdminPage(ModelAndView modelAndView, HttpServletRequest request) {
        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        modelAndView.setViewName("account/changePassword");

        User user1 = userService.findByUsername(user.getUsername());
        modelAndView.addObject("user", user1);

        return modelAndView;
    }

    @RequestMapping(value = "/account/changePassword", method = RequestMethod.POST)
    public ModelAndView updatePassword(ModelAndView modelAndView,
                                       @Valid User user,
                                       BindingResult bindingResult,
                                       HttpServletRequest request) {
        modelAndView.setViewName("/account/changePassword");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        modelAndView.addObject("successMessage", "Password changed successfully");
        return modelAndView;
    }
}
