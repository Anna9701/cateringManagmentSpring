package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.DishOrderEntityService;
import com.annawyrwal.model.DishOrdersEntity;
import com.annawyrwal.repository.Interfaces.DishOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DishOrderEntityServiceImpl implements DishOrderEntityService {
    private DishOrdersRepository dishOrdersRepository;

    @Autowired
    public DishOrderEntityServiceImpl(DishOrdersRepository dishOrdersRepository) {
        this.dishOrdersRepository = dishOrdersRepository;
    }

    @Transactional
    @Override
    public void addDishOrderEntity(DishOrdersEntity dishOrdersEntity) {
        dishOrdersRepository.addDishOrderEntity(dishOrdersEntity);
    }

    @Transactional
    @Override
    public List<DishOrdersEntity> getAllDishOrdersEntities() {
        return dishOrdersRepository.getAllDishOrdersEntities();
    }

    @Transactional
    @Override
    public void deleteDishOrderEntity(Integer orderId) {
        dishOrdersRepository.deleteDishOrderEntity(orderId);
    }

    @Transactional
    @Override
    public DishOrdersEntity updateDishOrderEntity(DishOrdersEntity dishOrdersEntity) {
        return dishOrdersRepository.updateDishOrderEntity(dishOrdersEntity);
    }

    @Transactional
    @Override
    public List<DishOrdersEntity> getDishOrderEntityByOrder(int orderId) {
        return dishOrdersRepository.getDishOrderEntityByOrder(orderId);
    }

    @Transactional
    @Override
    public List<DishOrdersEntity> getDishOrderEntityByDish(int dishId) {
        return dishOrdersRepository.getDishOrderEntityByDish(dishId);
    }

    @Transactional
    @Override
    public DishOrdersEntity getDishOrderEntity(int orderId, int dishId) {
        return dishOrdersRepository.getDishOrderEntity(orderId, dishId);
    }
}
