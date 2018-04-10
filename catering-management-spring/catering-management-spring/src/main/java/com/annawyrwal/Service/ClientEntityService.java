package com.annawyrwal.Service;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;

import java.util.List;

public interface ClientEntityService  {
    ClientsEntity findByUser(User user);
    public void addClient(ClientsEntity clientsEntity);
    public List<ClientsEntity> getAllClients();
    public void deleteClient(Integer clientId);
    public ClientsEntity getClient(int clientId);
    public ClientsEntity updateClient(ClientsEntity clientsEntity);

}
