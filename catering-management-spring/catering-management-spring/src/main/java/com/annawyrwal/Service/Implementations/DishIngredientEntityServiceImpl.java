package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.DishIngredientEntityService;
import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
import com.annawyrwal.repository.Interfaces.DishIngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DishIngredientEntityServiceImpl implements DishIngredientEntityService {
    private DishIngredientsRepository dishIngredientsRepository;

    @Autowired
    public DishIngredientEntityServiceImpl(DishIngredientsRepository dishIngredientsRepository) {
        this.dishIngredientsRepository = dishIngredientsRepository;
    }

    @Transactional
    @Override
    public void addDishIngredientEntity(DishIngredientsEntity dishIngredientEntity) {
        dishIngredientsRepository.addDishIngredientEntity(dishIngredientEntity);
    }

    @Transactional
    @Override
    public List<DishIngredientsEntity> getAllDishIngredientsEntities() {
        return dishIngredientsRepository.getAllDishIngredientsEntities();
    }

    @Transactional
    @Override
    public void deleteDishIngredientEntity(Integer dishIngredientId) {
        dishIngredientsRepository.deleteDishIngredientEntity(dishIngredientId);
    }

    @Transactional
    @Override
    public DishIngredientsEntity updateDishIngredientEntity(DishIngredientsEntity dishIngredientEntity) {
        return dishIngredientsRepository.updateDishIngredientEntity(dishIngredientEntity);
    }

    @Transactional
    @Override
    public List<DishIngredientsEntity> getDishIngredientEntityByIngredient(IngredientsEntity ingredient) {
        return dishIngredientsRepository.getDishIngredientEntityByIngredient(ingredient);
    }

    @Transactional
    @Override
    public List<DishIngredientsEntity> getDishIngredientEntityByDish(DishesEntity dish) {
        return dishIngredientsRepository.getDishIngredientEntityByDish(dish);
    }

    @Transactional
    @Override
    public DishIngredientsEntity getDishIngredientEntity(IngredientsEntity ingredient, DishesEntity dish) {
        return dishIngredientsRepository.getDishIngredientEntity(ingredient, dish);
    }

    @Transactional
    @Override
    public DishIngredientsEntity getDishIngredientEntity(int dishIngredientId) {
        return dishIngredientsRepository.getDishIngredientEntity(dishIngredientId);
    }
}
