package com.annawyrwal.repository.Services;

import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
import com.annawyrwal.repository.Interfaces.DishIngredientsRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishIngredientsRepositoryImpl implements DishIngredientsRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public DishIngredientsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addDishIngredientEntity(DishIngredientsEntity dishIngredientEntity) {
        getCurrentSession().saveOrUpdate(dishIngredientEntity);
    }

    @Override
    public List<DishIngredientsEntity> getAllDishIngredientsEntities() {
        return getCurrentSession().createQuery("from DishIngredientsEntity").list();
    }

    @Override
    public void deleteDishIngredientEntity(Integer dishIngredientId) {
        getCurrentSession().delete(getDishIngredientEntity(dishIngredientId));
    }

    @Override
    public DishIngredientsEntity updateDishIngredientEntity(DishIngredientsEntity dishIngredientEntity) {
        getCurrentSession().update(dishIngredientEntity);
        return dishIngredientEntity;
    }

    @Override
    public List<DishIngredientsEntity> getDishIngredientEntityByIngredient(IngredientsEntity ingredient) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishIngredientsEntity.class);
        criteriaQuery.add(Restrictions.eq("ingredientById", ingredient));
        return (List<DishIngredientsEntity>) criteriaQuery.list();
    }

    @Override
    public List<DishIngredientsEntity> getDishIngredientEntityByDish(DishesEntity dish) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishIngredientsEntity.class);
        criteriaQuery.add(Restrictions.eq("dishById", dish));
        return (List<DishIngredientsEntity>) criteriaQuery.list();
    }

    @Override
    public DishIngredientsEntity getDishIngredientEntity(IngredientsEntity ingredient, DishesEntity dish) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(DishIngredientsEntity.class);
        criteriaQuery.add(Restrictions.eq("ingredientById", ingredient));
        criteriaQuery.add(Restrictions.eq("dishById", dish));
        return (DishIngredientsEntity) criteriaQuery.list().get(0);
    }

    @Override
    public DishIngredientsEntity getDishIngredientEntity(int dishIngredientId) {
        return getCurrentSession().get(DishIngredientsEntity.class, dishIngredientId);
    }
}
