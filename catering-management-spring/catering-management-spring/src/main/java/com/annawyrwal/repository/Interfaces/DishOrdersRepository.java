package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.DishOrdersEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.OrdersEntity;

import java.util.List;

public interface DishOrdersRepository {
    void addDishOrderEntity(DishOrdersEntity dishOrdersEntity);
    List<DishOrdersEntity> getAllDishOrdersEntities();
    void deleteDishOrderEntity(Integer dishOrderId);
    DishOrdersEntity updateDishOrderEntity(DishOrdersEntity dishOrdersEntity);
    List<DishOrdersEntity> getDishOrderEntityByOrder (OrdersEntity order);
    List<DishOrdersEntity> getDishOrderEntityByDish (DishesEntity dish);
    DishOrdersEntity getDishOrderEntity (OrdersEntity order, DishesEntity dish);
    DishOrdersEntity getDishOrderEntity (int dishOrderId);
    //TODO: check relation many to many!!!!
    //TODO: get all dishes by order id
    //TODO: get all orders by dish ?? not necessary I think
}
