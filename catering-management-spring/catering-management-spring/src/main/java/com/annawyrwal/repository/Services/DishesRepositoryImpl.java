package com.annawyrwal.repository.Services;

import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.repository.Interfaces.DishesRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishesRepositoryImpl implements DishesRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public DishesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addDishEntity(DishesEntity dishEntity) {
        getCurrentSession().saveOrUpdate(dishEntity);
    }

    @Override
    public List<DishesEntity> getAllDishEntities() {
        return getCurrentSession().createQuery("from DishesEntity").list();
    }

    @Override
    public void deleteDishEntity(Integer dishId) {
        getCurrentSession().delete(dishId);
    }

    @Override
    public DishesEntity updateDishEntity(DishesEntity dishEntity) {
        getCurrentSession().saveOrUpdate(dishEntity);
        return dishEntity;
    }

    @Override
    public DishesEntity getDishEntity(int dishId) {
        return (DishesEntity) getCurrentSession().get(DishesEntity.class, dishId);
    }

    @Override
    public DishesEntity getDishEntity(String dishName) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishesEntity.class);
        criteriaQuery.add(Restrictions.eq("name", dishName));
        return (DishesEntity) criteriaQuery.list().get(0);
    }
}
