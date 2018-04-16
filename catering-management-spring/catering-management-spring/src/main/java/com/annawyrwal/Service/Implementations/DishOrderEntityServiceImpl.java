package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.DishOrderEntityService;
import com.annawyrwal.model.DishOrdersEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.OrdersEntity;
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
    public List<DishOrdersEntity> getDishOrderEntityByOrder(OrdersEntity order) {
        return dishOrdersRepository.getDishOrderEntityByOrder(order);
    }

    @Transactional
    @Override
    public List<DishOrdersEntity> getDishOrderEntityByDish(DishesEntity dish) {
        return dishOrdersRepository.getDishOrderEntityByDish(dish);
    }

    @Transactional
    @Override
    public DishOrdersEntity getDishOrderEntity(OrdersEntity order, DishesEntity dish) {
        return dishOrdersRepository.getDishOrderEntity(order, dish);
    }

    @Override
    public DishOrdersEntity getDishOrderEntity(int dishOrderId) {
        return dishOrdersRepository.getDishOrderEntity(dishOrderId);
    }
}
