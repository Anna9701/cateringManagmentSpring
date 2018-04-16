package com.annawyrwal.repository.Services;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
import com.annawyrwal.repository.Interfaces.ClientRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ClientsEntity findByUser(User user) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(ClientsEntity.class);
        criteriaQuery.add(Restrictions.like("userByUsername", user));
        return (ClientsEntity) criteriaQuery.list().get(0);
    }

    @Override
    public void addClientEntity(ClientsEntity clientsEntity) {
        getCurrentSession().saveOrUpdate(clientsEntity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ClientsEntity> getAllClientsEntities() {
        return getCurrentSession().createQuery("from ClientsEntity").list();
    }

    @Override
    public void deleteClientEntity(Integer clientId) {
        ClientsEntity clientsEntity = (ClientsEntity) getCurrentSession().load(ClientsEntity.class, clientId);
        if (clientsEntity != null) {
            getCurrentSession().delete(clientsEntity);
        }
    }

    @Override
    public ClientsEntity updateClientEntity(ClientsEntity clientsEntity) {
        getCurrentSession().update(clientsEntity);
        return clientsEntity;
    }

    @Override
    public ClientsEntity getClientEntity(int clientId) {
        return (ClientsEntity) getCurrentSession().get(ClientsEntity.class, clientId);
    }
}
