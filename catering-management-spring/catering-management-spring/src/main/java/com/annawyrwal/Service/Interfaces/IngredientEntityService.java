package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;

import java.util.List;

public interface IngredientEntityService {
    void addIngredientsEntity(IngredientsEntity ingredientEntity);
    List<IngredientsEntity> getAllIngredientsEntity();
    void deleteIngredientsEntity(Integer ingredientId);
    IngredientsEntity updateIngredientsEntity(IngredientsEntity ingredientEntity);
    IngredientsEntity getIngredientsEntity (int ingredientId);
    IngredientsEntity getIngredientsEntity (String ingredientName);
    List<IngredientsEntity> getIngredientsByDishEntity(DishesEntity dishesEntity);
}
