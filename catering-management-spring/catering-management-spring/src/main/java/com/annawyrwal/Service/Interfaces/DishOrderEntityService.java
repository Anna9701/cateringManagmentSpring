package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.DishOrdersEntity;

import java.util.List;

public interface DishOrderEntityService {
    void addDishOrderEntity(DishOrdersEntity dishOrdersEntity);
    List<DishOrdersEntity> getAllDishOrdersEntities();
    void deleteDishOrderEntity(Integer dishOrderId);
    DishOrdersEntity updateDishOrderEntity(DishOrdersEntity dishOrdersEntity);
    List<DishOrdersEntity> getDishOrderEntityByOrder (int orderId);
    List<DishOrdersEntity> getDishOrderEntityByDish (int dishId);
    DishOrdersEntity getDishOrderEntity (int orderId, int dishId);
}
