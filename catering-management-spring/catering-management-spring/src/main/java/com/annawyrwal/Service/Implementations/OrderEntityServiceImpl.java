package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.OrderEntityService;
import com.annawyrwal.model.*;
import com.annawyrwal.repository.Interfaces.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderEntityServiceImpl implements OrderEntityService {
    private OrdersRepository ordersRepository;

    @Autowired
    public OrderEntityServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public void addOrderEntity(OrdersEntity orderEntity) {
        ordersRepository.addOrderEntity(orderEntity);
    }

    @Override
    public List<OrdersEntity> getAllOrderEntity() {
        return ordersRepository.getAllOrderEntity();
    }

    @Override
    public void deleteOrderEntity(Integer orderId) {
        ordersRepository.deleteOrderEntity(orderId);
    }

    @Override
    public OrdersEntity updateOrderEntity(OrdersEntity orderEntity) {
        return ordersRepository.updateOrderEntity(orderEntity);
    }

    @Override
    public OrdersEntity getOrderEntity(int orderId) {
        return ordersRepository.getOrderEntity(orderId);
    }

    @Override
    public List<OrdersEntity> getOrdersByCateringsEntity(CateringsEntity cateringsEntity) {
        return ordersRepository.getOrdersByCateringsEntity(cateringsEntity);
    }

    @Override
    public List<OrdersEntity> getOrdersByClientsEntity(ClientsEntity clientsEntity) {
        return ordersRepository.getOrdersByClientsEntity(clientsEntity);
    }

    @Override
    public List<OrdersEntity> getOrdersByPlacesEntity(PlacesEntity placesEntity) {
        return ordersRepository.getOrdersByPlacesEntity(placesEntity);
    }

    @Override
    public List<OrdersEntity> getOrdersByDatesEntity(DatesEntity datesEntity) {
        return ordersRepository.getOrdersByDatesEntity(datesEntity);
    }
}
