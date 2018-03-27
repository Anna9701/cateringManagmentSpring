package com.annawyrwal.service;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import com.annawyrwal.repository.ContactDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactDataService {
    ContactDataRepository contactDataRepository;

    @Autowired
    public ContactDataService(ContactDataRepository contactDataRepository) {
        this.contactDataRepository = contactDataRepository;
    }

    public void saveContactData(ContactDataEntity contactDataEntity) {
        contactDataRepository.save(contactDataEntity);
    }

    public ContactDataEntity findByClientid (ClientsEntity clientsEntity) {
        return contactDataRepository.findByClientsByClientid(clientsEntity);
    }
}
