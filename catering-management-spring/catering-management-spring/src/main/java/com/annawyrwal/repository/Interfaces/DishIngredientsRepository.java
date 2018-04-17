package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.*;

import java.util.List;

public interface DishIngredientsRepository {
    void addDishIngredientEntity(DishIngredientsEntity dishIngredientEntity);
    List<DishIngredientsEntity> getAllDishIngredientsEntities();
    void deleteDishIngredientEntity(Integer dishIngredientId);
    DishIngredientsEntity updateDishIngredientEntity(DishIngredientsEntity dishIngredientEntity);
    List<DishIngredientsEntity> getDishIngredientEntityByIngredient(IngredientsEntity ingredient);
    List<DishIngredientsEntity> getDishIngredientEntityByDish(DishesEntity dish);
    DishIngredientsEntity getDishIngredientEntity(IngredientsEntity ingredient, DishesEntity dish);
    DishIngredientsEntity getDishIngredientEntity(int dishIngredientId);
}
