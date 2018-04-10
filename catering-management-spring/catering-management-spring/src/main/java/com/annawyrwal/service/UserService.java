package com.annawyrwal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.annawyrwal.model.User;
import com.annawyrwal.repository.UserRepository;

import javax.annotation.PostConstruct;

@Service("userService")
public class UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("${user.administrator.name}")
	String adminName;

	@Value("${user.administrator.password}")
	String adminPassword;

    @Value("${user.administrator.email}")
    String adminEmail;

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;

	}

	@PostConstruct
	private void createAdministratorAccount() {
		if (findByUsername(adminName) != null) {
			return;
		}
		User admin = new User();
		admin.setUsername(adminName);
        admin.setPassword(bCryptPasswordEncoder.encode(adminPassword));
		admin.setEnabled(true);
		admin.setEmail(adminEmail);
		admin.setLastName("Administrator");
		admin.setRole("ADMIN");
		saveUser(admin);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByUsername(String username) {
	    return userRepository.findByUsername(username);
    }

	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}


}