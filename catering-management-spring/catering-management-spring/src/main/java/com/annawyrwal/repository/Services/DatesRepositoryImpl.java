package com.annawyrwal.repository.Services;

import com.annawyrwal.model.DatesEntity;
import com.annawyrwal.repository.Interfaces.DatesRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatesRepositoryImpl implements DatesRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public DatesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addDateEntity(DatesEntity dateEntity) {
        getCurrentSession().saveOrUpdate(dateEntity);
    }

    @Override
    public List<DatesEntity> getAllDatesEntities() {
        return getCurrentSession().createQuery("from DatesEntity").list();
    }

    @Override
    public void deleteDateEntity(Integer dateId) {
        getCurrentSession().delete(dateId);
    }

    @Override
    public DatesEntity updateDateEntity(DatesEntity dateEntity) {
        getCurrentSession().saveOrUpdate(dateEntity);
        return dateEntity;
    }

    @Override
    public DatesEntity getDateEntity(int dateId) {
        return (DatesEntity) getCurrentSession().get(DatesEntity.class, dateId);
    }
}
