package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.IngredientEntityService;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
import com.annawyrwal.repository.Interfaces.IngredientsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IngredientEntityServiceImpl implements IngredientEntityService {
    private IngredientsRepository ingredientsRepository;

    public IngredientEntityServiceImpl(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Transactional
    @Override
    public void addIngredientsEntity(IngredientsEntity ingredientEntity) {
        ingredientsRepository.addIngredientsEntity(ingredientEntity);
    }

    @Transactional
    @Override
    public List<IngredientsEntity> getAllIngredientsEntity() {
        return ingredientsRepository.getAllIngredientsEntity();
    }

    @Transactional
    @Override
    public void deleteIngredientsEntity(Integer ingredientId) {
        ingredientsRepository.deleteIngredientsEntity(ingredientId);
    }

    @Transactional
    @Override
    public IngredientsEntity updateIngredientsEntity(IngredientsEntity ingredientEntity) {
        return ingredientsRepository.updateIngredientsEntity(ingredientEntity);
    }

    @Transactional
    @Override
    public IngredientsEntity getIngredientsEntity(int ingredientId) {
        return ingredientsRepository.getIngredientsEntity(ingredientId);
    }

    @Transactional
    @Override
    public IngredientsEntity getIngredientsEntity(String ingredientName) {
        return ingredientsRepository.getIngredientsEntity(ingredientName);
    }

    @Transactional
    @Override
    public List<IngredientsEntity> getIngredientsByDishEntity(DishesEntity dishesEntity) {
        return ingredientsRepository.getIngredientsByDishEntity(dishesEntity);
    }
}