package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;

import java.util.List;

public interface DishIngredientEntityService {
    void addDishIngredientEntity(DishIngredientsEntity dishIngredientEntity);
    List<DishIngredientsEntity> getAllDishIngredientsEntities();
    void deleteDishIngredientEntity(Integer dishIngredientId);
    DishIngredientsEntity updateDishIngredientEntity(DishIngredientsEntity dishIngredientEntity);
    List<DishIngredientsEntity> getDishIngredientEntityByIngredient(IngredientsEntity ingredient);
    List<DishIngredientsEntity> getDishIngredientEntityByDish(DishesEntity dish);
    DishIngredientsEntity getDishIngredientEntity(IngredientsEntity ingredient, DishesEntity dish);
    DishIngredientsEntity getDishIngredientEntity(int dishIngredientId);
}
