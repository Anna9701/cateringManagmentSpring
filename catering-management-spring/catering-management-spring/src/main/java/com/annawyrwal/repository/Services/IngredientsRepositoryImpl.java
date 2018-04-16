package com.annawyrwal.repository.Services;

import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
import com.annawyrwal.repository.Interfaces.IngredientsRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientsRepositoryImpl implements IngredientsRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public IngredientsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void addIngredientsEntity(IngredientsEntity ingredientEntity) {
        getCurrentSession().saveOrUpdate(ingredientEntity);
    }

    @Override
    public List<IngredientsEntity> getAllIngredientsEntity() {
        return getCurrentSession().createQuery("from IngredientsEntity").list();
    }

    @Override
    public void deleteIngredientsEntity(Integer ingredientId) {
        getCurrentSession().delete(ingredientId);
    }

    @Override
    public IngredientsEntity updateIngredientsEntity(IngredientsEntity ingredientEntity) {
        getCurrentSession().saveOrUpdate(ingredientEntity);
        return ingredientEntity;
    }

    @Override
    public IngredientsEntity getIngredientsEntity(int ingredientId) {
        return getCurrentSession().get(IngredientsEntity.class, ingredientId);
    }

    @Override
    public IngredientsEntity getIngredientsEntity(String ingredientName) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(IngredientsEntity.class);
        criteriaQuery.add(Restrictions.eq("name", ingredientName));
        return (IngredientsEntity) criteriaQuery.list().get(0);
    }

    @Override
    public List<IngredientsEntity> getIngredientsByDishEntity(DishesEntity dishesEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(IngredientsEntity.class);
        criteriaQuery.add(Restrictions.eq("dishesByDishid", dishesEntity));
        return (List<IngredientsEntity>) criteriaQuery.list();
    }
}
