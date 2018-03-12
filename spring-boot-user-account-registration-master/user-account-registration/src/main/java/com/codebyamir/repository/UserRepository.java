package com.codebyamir.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codebyamir.model.User;

public interface UserRepository extends CrudRepository<User, String> {
	 User findByEmail(String email);
	 User findByConfirmationToken(String confirmationToken);
	 User findByUsername(String username);
}