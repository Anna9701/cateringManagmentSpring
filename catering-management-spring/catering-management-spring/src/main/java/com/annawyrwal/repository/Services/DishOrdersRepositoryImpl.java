package com.annawyrwal.repository.Services;

import com.annawyrwal.model.DishOrdersEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.OrdersEntity;
import com.annawyrwal.repository.Interfaces.DishOrdersRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
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
        return getCurrentSession().createQuery("from DishOrdersEntity").list();
    }

    @Override
    public void deleteDishOrderEntity(Integer orderId) {
        getCurrentSession().delete(getDishOrderEntity(orderId));
    }

    @Override
    public DishOrdersEntity updateDishOrderEntity(DishOrdersEntity dishOrdersEntity) {
        getCurrentSession().update(dishOrdersEntity);
        return dishOrdersEntity;
    }

    @Override
    public List<DishOrdersEntity> getDishOrderEntityByOrder(OrdersEntity order) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishOrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("orderById", order));
        return (List<DishOrdersEntity>) criteriaQuery.list();
    }

    @Override
    public List<DishOrdersEntity> getDishOrderEntityByDish(DishesEntity dish) {
        Criteria criteria = getCurrentSession().createCriteria(DishOrdersEntity.class);
        criteria.add(Restrictions.eq("dishById", dish));
        return (List<DishOrdersEntity>) criteria.list();
    }

    @Override
    public DishOrdersEntity getDishOrderEntity(OrdersEntity order, DishesEntity dish) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishOrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("orderById", order));
        criteriaQuery.add(Restrictions.eq("dishById", dish));
        return (DishOrdersEntity) criteriaQuery.list().get(0);
    }

    @Override
    public DishOrdersEntity getDishOrderEntity(int dishOrderId) {
        return getCurrentSession().get(DishOrdersEntity.class, dishOrderId);
    }
}
