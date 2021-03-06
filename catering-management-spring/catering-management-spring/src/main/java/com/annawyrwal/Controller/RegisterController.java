package com.annawyrwal.Controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.Service.Interfaces.ContactDataService;
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

import com.annawyrwal.model.User;
import com.annawyrwal.Service.Authentication.EmailService;
import com.annawyrwal.Service.Authentication.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	private EmailService emailService;
	private ClientEntityService clientEntityService;
	private ContactDataService contactDataService;

	@Autowired
	public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder,
			UserService userService, EmailService emailService,
		    ClientEntityService clientEntityService, ContactDataService contactDataService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userService = userService;
		this.emailService = emailService;
		this.clientEntityService = clientEntityService;
		this.contactDataService = contactDataService;
	}
	
	// Return registration form template
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());
		
		System.out.println(userExists);
		
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("register");		
		} else { // new user so we create user and send confirmation e-mail
			// Set new password
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRole("USER");
			// Set user to enabled
			user.setEnabled(false);

		    user.setConfirmationToken(UUID.randomUUID().toString());

            // Save user
            userService.saveUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			addUserToClients(user); // for now each new user will be client

			modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			modelAndView.setViewName("register");
		}
			
		return modelAndView;
	}

	private void addUserToClients(User user) {
        ClientsEntity clientAccount = new ClientsEntity();
        clientAccount.setLastName(user.getLastName());
        clientAccount.setUserByUsername(user);
        clientEntityService.addClient(clientAccount);
		addUserContactData(clientAccount, user);
    }

    private void addUserContactData(ClientsEntity clientsEntity, User user) {
		ContactDataEntity contactDataEntity = new ContactDataEntity();
		contactDataEntity.setClientsByClientid(clientsEntity);
		contactDataEntity.setEmail(user.getEmail());
		contactDataService.addContactData(contactDataEntity);
	}

	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {

		User user = userService.findByConfirmationToken(token);

		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			user.setEnabled(true);
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Your account is activated. Now you can log in");
		}

		modelAndView.setViewName("confirm");
		return modelAndView;
	}
	
}