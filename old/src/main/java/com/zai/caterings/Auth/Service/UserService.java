package com.zai.caterings.Auth.Service;
import com.zai.caterings.Models.UsersEntity;
import com.zai.caterings.Repositories.RoleRepository;
import com.zai.caterings.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service("userService")
public class UserService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(UsersEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
     //   user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    public UsersEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UsersEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UsersEntity findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }
}
