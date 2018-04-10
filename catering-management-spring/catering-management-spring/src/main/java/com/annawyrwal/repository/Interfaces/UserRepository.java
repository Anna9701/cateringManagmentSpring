package com.annawyrwal.repository.Interfaces;

import org.springframework.data.repository.CrudRepository;

import com.annawyrwal.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	 User findByEmail(String email);
	 User findByConfirmationToken(String confirmationToken);
	 User findByUsername(String username);
}