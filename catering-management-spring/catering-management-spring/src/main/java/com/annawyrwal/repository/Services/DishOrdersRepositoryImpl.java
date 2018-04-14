package com.annawyrwal.repository.Services;

import com.annawyrwal.model.DishOrdersEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.repository.Interfaces.DishOrdersRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishOrdersRepositoryImpl implements DishOrdersRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public DishOrdersRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addDishOrderEntity(DishOrdersEntity dishOrdersEntity) {
        getCurrentSession().saveOrUpdate(dishOrdersEntity);
    }

    @Override
    public List<DishOrdersEntity> getAllDishOrdersEntities() {
        return getCurrentSession().createQuery("from dish_orders").list();
    }

    @Override
    public void deleteDishOrderEntity(Integer orderId) {
        getCurrentSession().delete(orderId);
    }

    @Override
    public DishOrdersEntity updateDishOrderEntity(DishOrdersEntity dishOrdersEntity) {
        getCurrentSession().update(dishOrdersEntity);
        return dishOrdersEntity;
    }

    @Override
    public List<DishOrdersEntity> getDishOrderEntityByOrder(int orderId) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishOrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("orderid", orderId));
        return (List<DishOrdersEntity>) criteriaQuery.list();
    }

    @Override
    public List<DishOrdersEntity> getDishOrderEntityByDish(int dishId) {
        return null;
    }

    @Override
    public DishOrdersEntity getDishOrderEntity(int orderId, int dishId) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishOrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("orderid", orderId));
        criteriaQuery.add(Restrictions.eq("dishid", dishId));
        return (DishOrdersEntity) criteriaQuery.list().get(0);
    }
}
