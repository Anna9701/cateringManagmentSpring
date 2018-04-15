package com.annawyrwal.repository.Services;

import com.annawyrwal.model.*;
import com.annawyrwal.repository.Interfaces.OrdersRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public OrdersRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addOrderEntity(OrdersEntity orderEntity) {
        getCurrentSession().saveOrUpdate(orderEntity);
    }

    @Override
    public List<OrdersEntity> getAllOrderEntity() {
        return getCurrentSession().createQuery("from orders").list();
    }

    @Override
    public void deleteOrderEntity(Integer orderId) {
        getCurrentSession().delete(getOrderEntity(orderId));
    }

    @Override
    public OrdersEntity updateOrderEntity(OrdersEntity orderEntity) {
        getCurrentSession().update(orderEntity);
        return orderEntity;
    }

    @Override
    public OrdersEntity getOrderEntity(int orderId) {
        return getCurrentSession().get(OrdersEntity.class, orderId);
    }

    @Override
    public List<OrdersEntity> getOrdersByCateringsEntity(CateringsEntity cateringsEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(OrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("cateringsByCateringid", cateringsEntity));
        return (List<OrdersEntity>) criteriaQuery.list();
    }

    @Override
    public List<OrdersEntity> getOrdersByClientsEntity(ClientsEntity clientsEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(OrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("clientsByClientid", clientsEntity));
        return (List<OrdersEntity>) criteriaQuery.list();
    }

    @Override
    public List<OrdersEntity> getOrdersByPlacesEntity(PlacesEntity placesEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(OrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("placesByPlaceid", placesEntity));
        return (List<OrdersEntity>) criteriaQuery.list();
    }

    @Override
    public List<OrdersEntity> getOrdersByDatesEntity(DatesEntity datesEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(OrdersEntity.class);
        criteriaQuery.add(Restrictions.eq("datesByDateid", datesEntity));
        return (List<OrdersEntity>) criteriaQuery.list();
    }
}
