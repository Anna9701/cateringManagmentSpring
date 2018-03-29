package com.annawyrwal.repository;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactDataRepositoryImpl implements ContactDataRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public ContactDataRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ContactDataEntity findByClient(ClientsEntity clientsEntity) {
        Criteria criteriaQuery = getCurrentSession().createCriteria(ContactDataEntity.class);
        criteriaQuery.add(Restrictions.eq("clientsByClientid", clientsEntity));
        return (ContactDataEntity) criteriaQuery.list().get(0);
    }

    @Override
    public void addContactDataEntity(ContactDataEntity contactDataEntity) {
        getCurrentSession().saveOrUpdate(contactDataEntity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ContactDataEntity> getAllContactDataEntities() {
        return getCurrentSession().createQuery("from contact_data").list();
    }

    @Override
    public void deleteContactDataEntity(Integer contactDataId) {
        getCurrentSession().delete(contactDataId);
    }

    @Override
    public ContactDataEntity updateContactDataEntity(ContactDataEntity contactDataEntity) {
        contactDataEntity.setId(contactDataEntity.getId() + 1); //TODO workaround
        getCurrentSession().update(contactDataEntity);
        return contactDataEntity;
    }

    @Override
    public ContactDataEntity getContactDataEntity(int contactDataId) {
        return (ContactDataEntity) getCurrentSession().get(ContactDataEntity.class, contactDataId);
    }
}
