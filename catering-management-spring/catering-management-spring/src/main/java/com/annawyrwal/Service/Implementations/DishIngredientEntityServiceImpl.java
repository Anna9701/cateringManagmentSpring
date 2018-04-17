package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.DishIngredientEntityService;
import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishIngredientEntityServiceImpl implements DishIngredientEntityService {
    private DishIngredientEntityService dishIngredientEntityService;

    @Autowired
    public DishIngredientEntityServiceImpl(DishIngredientEntityService dishIngredientEntityService) {
        this.dishIngredientEntityService = dishIngredientEntityService;
    }

    @Override
    public void addDishIngredientEntity(DishIngredientsEntity dishIngredientEntity) {
        dishIngredientEntityService.addDishIngredientEntity(dishIngredientEntity);
    }

    @Override
    public List<DishIngredientsEntity> getAllDishIngredientsEntities() {
        return dishIngredientEntityService.getAllDishIngredientsEntities();
    }

    @Override
    public void deleteDishIngredientEntity(Integer dishIngredientId) {
        dishIngredientEntityService.deleteDishIngredientEntity(dishIngredientId);
    }

    @Override
    public DishIngredientsEntity updateDishIngredientEntity(DishIngredientsEntity dishIngredientEntity) {
        return dishIngredientEntityService.updateDishIngredientEntity(dishIngredientEntity);
    }

    @Override
    public List<DishIngredientsEntity> getDishIngredientEntityByIngredient(IngredientsEntity ingredient) {
        return dishIngredientEntityService.getDishIngredientEntityByIngredient(ingredient);
    }

    @Override
    public List<DishIngredientsEntity> getDishIngredientEntityByDish(DishesEntity dish) {
        return dishIngredientEntityService.getDishIngredientEntityByDish(dish);
    }

    @Override
    public DishIngredientsEntity getDishIngredientEntity(IngredientsEntity ingredient, DishesEntity dish) {
        return dishIngredientEntityService.getDishIngredientEntity(ingredient, dish);
    }

    @Override
    public DishIngredientsEntity getDishIngredientEntity(int dishIngredientId) {
        return dishIngredientEntityService.getDishIngredientEntity(dishIngredientId);
    }
}
