package com.annawyrwal.service;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import com.annawyrwal.repository.ClientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClientEntityService  {
    ClientsEntity findByUser(User user);
    public void addClient(ClientsEntity clientsEntity);
    public List<ClientsEntity> getAllClients();
    public void deleteClient(Integer clientId);
    public ClientsEntity getClient(int clientId);
    public ClientsEntity updateClient(ClientsEntity clientsEntity);

}
