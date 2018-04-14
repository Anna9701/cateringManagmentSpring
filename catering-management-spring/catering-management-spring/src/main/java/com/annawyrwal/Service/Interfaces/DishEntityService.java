package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.DishesEntity;

import java.util.List;

public interface DishEntityService {
    void addDishEntity(DishesEntity dishEntity);
    List<DishesEntity> getAllDishEntities();
    void deleteDishEntity(Integer dishId);
    DishesEntity updateDishEntity(DishesEntity dishEntity);
    DishesEntity getDishEntity (int dishId);
    DishesEntity getDishEntity (String dishName);
}
