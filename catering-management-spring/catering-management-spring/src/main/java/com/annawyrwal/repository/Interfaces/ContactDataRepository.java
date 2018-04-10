package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactDataRepository {
    ContactDataEntity findByClient(ClientsEntity clientsEntity);
    void addContactDataEntity(ContactDataEntity contactDataEntity);
    List<ContactDataEntity> getAllContactDataEntities();
    void deleteContactDataEntity(Integer contactDataId);
    ContactDataEntity updateContactDataEntity(ContactDataEntity contactDataEntity);
    ContactDataEntity getContactDataEntity (int contactDataId);
}
