package com.annawyrwal.service;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import com.annawyrwal.repository.ClientRepository;
import com.annawyrwal.repository.ContactDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactDataServiceImpl implements ContactDataService {
    @Autowired
    private ContactDataRepository contactDataRepository;

    @Override
    public ContactDataEntity findByClient(ClientsEntity clientsEntity) {
        return contactDataRepository.findByClient(clientsEntity);
    }

    @Transactional
    @Override
    public void addContactData(ContactDataEntity contactDataEntity) {
        contactDataRepository.addContactDataEntity(contactDataEntity);
    }

    @Transactional
    @Override
    public List<ContactDataEntity> getAllContactData() {
        return contactDataRepository.getAllContactDataEntities();
    }

    @Transactional
    @Override
    public void deleteContactData(Integer contactDataId) {
        contactDataRepository.deleteContactDataEntity(contactDataId);
    }

    @Transactional
    @Override
    public ContactDataEntity getContactData(int contactDataId) {
        return contactDataRepository.getContactDataEntity(contactDataId);
    }

    @Transactional
    @Override
    public ContactDataEntity updateContactData(ContactDataEntity contactDataEntity) {
        contactDataRepository.updateContactDataEntity(contactDataEntity);
        return contactDataEntity;
    }
}
