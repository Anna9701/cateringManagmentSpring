package com.annawyrwal.repository;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientsEntity, Integer> {
    ClientsEntity findByUserByUsername(User userByUsername);
    ClientsEntity findByLastName(String lastName);
}
