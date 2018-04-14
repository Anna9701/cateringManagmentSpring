package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import com.annawyrwal.repository.Interfaces.ClientRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ClientEntityServiceImpl implements ClientEntityService {
    private ClientRepository clientRepository;
   // private SessionFactory sessionFactory;

    @Autowired
    public ClientEntityServiceImpl(SessionFactory sessionFactory, ClientRepository clientRepository) {
  //      this.sessionFactory = sessionFactory;
        this.clientRepository = clientRepository;
    }


    @Override
    public ClientsEntity findByUser(User user) {
        return clientRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void addClient(ClientsEntity clientsEntity) {
        clientRepository.addClientEntity(clientsEntity);
    }

    @Override
    @Transactional
    public List<ClientsEntity> getAllClients() {
        return clientRepository.getAllClientsEntities();
    }

    @Override
    @Transactional
    public void deleteClient(Integer clientId) {
        clientRepository.deleteClientEntity(clientId);
    }

    @Override
    public ClientsEntity getClient(int clientId) {
        return clientRepository.getClientEntity(clientId);
    }

    @Override
    @Transactional
    public ClientsEntity updateClient(ClientsEntity clientsEntity) {
        return clientRepository.updateClientEntity(clientsEntity);
    }
}
