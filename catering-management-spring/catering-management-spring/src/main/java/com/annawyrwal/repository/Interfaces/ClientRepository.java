package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository  {
    ClientsEntity findByUser(User user);
    void addClientEntity(ClientsEntity clientsEntity);
    List<ClientsEntity> getAllClientsEntities();
    void deleteClientEntity(Integer clientId);
    ClientsEntity updateClientEntity(ClientsEntity clientsEntity);
    ClientsEntity getClientEntity (int clientId);
}
