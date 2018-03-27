package com.annawyrwal.service;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import com.annawyrwal.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("clientsService")
public class ClientEntityService  {
    ClientRepository clientRepository;

    @Autowired
    public ClientEntityService(ClientRepository userRepository) {
        this.clientRepository = userRepository;
    }

    public void saveClient(ClientsEntity clientsEntity) {
        clientRepository.save(clientsEntity);
    }

    public ClientsEntity findByUsername(User user) {
        return clientRepository.findByUserByUsername(user);
    }

    public ClientsEntity findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

}
