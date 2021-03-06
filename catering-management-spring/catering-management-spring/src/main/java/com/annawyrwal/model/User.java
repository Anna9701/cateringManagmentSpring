package com.annawyrwal.model;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

@Entity
@Table(name = "user", schema = "public", catalog = "catering")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
	private String email;
	
	@Column(name = "password")
	@Transient
	private String password;
	
	@Column(name = "last_name")
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "confirmation_token")
	private String confirmationToken;

	@Column(name = "role")
	private String role;
	
	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean value) {
		this.enabled = value;
	}


    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}