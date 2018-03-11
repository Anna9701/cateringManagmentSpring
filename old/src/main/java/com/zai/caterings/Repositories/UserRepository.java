package com.zai.caterings.Repositories;

import com.zai.caterings.Models.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
    UsersEntity findByUsername(String username);
    UsersEntity findByEmail(String email);
    UsersEntity findByConfirmationToken(String confirmationToken);
}
