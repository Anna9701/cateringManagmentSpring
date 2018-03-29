package com.annawyrwal.service;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import com.annawyrwal.model.User;
import com.annawyrwal.repository.ContactDataRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ContactDataService {
    ContactDataEntity findByClient(ClientsEntity clientsEntity);
    public void addContactData(ContactDataEntity contactDataEntity);
    public List<ContactDataEntity> getAllContactData();
    public void deleteContactData(Integer contactDataId);
    public ContactDataEntity getContactData(int contactDataId);
    public ContactDataEntity updateContactData(ContactDataEntity contactDataEntity);

}
