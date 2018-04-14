package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.DishEntityService;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.repository.Interfaces.DishesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DishEntityServiceImpl implements DishEntityService {
    private DishesRepository dishesRepository;

    @Autowired
    public DishEntityServiceImpl(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    @Transactional
    @Override
    public void addDishEntity(DishesEntity dishEntity) {
        dishesRepository.addDishEntity(dishEntity);
    }

    @Transactional
    @Override
    public List<DishesEntity> getAllDishEntities() {
        return dishesRepository.getAllDishEntities();
    }

    @Transactional
    @Override
    public void deleteDishEntity(Integer dishId) {
        dishesRepository.deleteDishEntity(dishId);
    }

    @Transactional
    @Override
    public DishesEntity updateDishEntity(DishesEntity dishEntity) {
        return dishesRepository.updateDishEntity(dishEntity);
    }

    @Transactional
    @Override
    public DishesEntity getDishEntity(int dishId) {
        return dishesRepository.getDishEntity(dishId);
    }

    @Transactional
    @Override
    public DishesEntity getDishEntity(String dishName) {
        return dishesRepository.getDishEntity(dishName);
    }
}
