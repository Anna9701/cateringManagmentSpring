package com.annawyrwal.repository.Services;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.PlacesEntity;
import com.annawyrwal.repository.Interfaces.PlacesRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlacesRepositoryImpl implements PlacesRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public PlacesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addPlaceEntity(PlacesEntity placeEntity) {
        getCurrentSession().saveOrUpdate(placeEntity);
    }

    @Override
    public List<PlacesEntity> getAllPlaceEntity() {
        return getCurrentSession().createQuery("from places").list();
    }

    @Override
    public void deletePlaceEntity(Integer placeId) {
        getCurrentSession().delete(placeId);
    }

    @Override
    public PlacesEntity updatePlaceEntity(PlacesEntity placeEntity) {
        getCurrentSession().update(placeEntity);
        return placeEntity;
    }

    @Override
    public PlacesEntity getPlaceEntity(int placeId) {
        return getCurrentSession().get(PlacesEntity.class, placeId);
    }

    @Override
    public List<PlacesEntity> getPlaceEntityByClientsEntity(ClientsEntity clientsEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(PlacesEntity.class);
        criteriaQuery.add(Restrictions.eq("clientsByClientid", clientsEntity));
        return (List<PlacesEntity>) criteriaQuery.list();
    }
}
