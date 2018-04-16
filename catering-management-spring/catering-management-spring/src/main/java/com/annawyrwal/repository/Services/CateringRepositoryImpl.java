package com.annawyrwal.repository.Services;

import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import com.annawyrwal.repository.Interfaces.CateringRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CateringRepositoryImpl implements CateringRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public CateringRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public CateringsEntity findById(Integer cateringId) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(CateringsEntity.class);
        criteriaQuery.add(Restrictions.eq("id", cateringId));
        return (CateringsEntity) criteriaQuery.list().get(0);
    }

    @Override
    public CateringsEntity findByName(String name) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(CateringsEntity.class);
        criteriaQuery.add(Restrictions.eq("name", name));
        return (CateringsEntity) criteriaQuery.list().get(0);
    }

    @Override
    public List<CateringsEntity> getAllCateringsEntities() {
        return getCurrentSession().createQuery("from CateringsEntity").list();
    }

    @Override
    public void deleteCateringEntity(Integer cateringId) {
        CateringsEntity cateringEntity = (CateringsEntity) getCurrentSession().load(CateringsEntity.class, cateringId);
        if (cateringEntity != null) {
            getCurrentSession().delete(cateringEntity);
        }
    }

    @Override
    public CateringsEntity updateCateringEntity(CateringsEntity cateringsEntity) {
        getCurrentSession().update(cateringsEntity);
        return cateringsEntity;
    }

    @Override
    public void addCateringEntity(CateringsEntity cateringsEntity) {
        getCurrentSession().saveOrUpdate(cateringsEntity);
    }

    @Override
    public List<CateringsEntity> getCateringsEntitiesByClient(ClientsEntity clientsEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(CateringsEntity.class);
        criteriaQuery.add(Restrictions.eq("clientsByClientid", clientsEntity));
        return (List<CateringsEntity>) criteriaQuery.list();
    }
}
