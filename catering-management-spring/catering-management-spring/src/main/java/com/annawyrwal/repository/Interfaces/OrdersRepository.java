package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.*;

import java.util.List;

public interface OrdersRepository {
    void addOrderEntity(OrdersEntity orderEntity);
    List<OrdersEntity> getAllOrderEntity();
    void deleteOrderEntity(Integer orderId);
    OrdersEntity updateOrderEntity(OrdersEntity orderEntity);
    OrdersEntity getOrderEntity (int orderId);
    List<OrdersEntity> getOrdersByCateringsEntity(CateringsEntity cateringsEntity);
    List<OrdersEntity> getOrdersByClientsEntity(ClientsEntity clientsEntity);
    List<OrdersEntity> getOrdersByPlacesEntity(PlacesEntity placesEntity);
    List<OrdersEntity> getOrdersByDatesEntity(DatesEntity datesEntity);
}
