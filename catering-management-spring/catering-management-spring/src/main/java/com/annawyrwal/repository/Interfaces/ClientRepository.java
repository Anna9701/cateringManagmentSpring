package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository  {
    ClientsEntity findByUser(User user);
    public void addClientEntity(ClientsEntity clientsEntity);
    public List<ClientsEntity> getAllClientsEntities();
    public void deleteClientEntity(Integer clientId);
    public ClientsEntity updateClientEntity(ClientsEntity clientsEntity);
    public ClientsEntity getClientEntity (int clientId);
}
