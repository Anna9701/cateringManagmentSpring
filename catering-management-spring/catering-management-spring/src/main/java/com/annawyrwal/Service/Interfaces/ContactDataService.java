package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;

import java.util.List;


public interface ContactDataService {
    ContactDataEntity findByClient(ClientsEntity clientsEntity);
    public void addContactData(ContactDataEntity contactDataEntity);
    public List<ContactDataEntity> getAllContactData();
    public void deleteContactData(Integer contactDataId);
    public ContactDataEntity getContactData(int contactDataId);
    public ContactDataEntity updateContactData(ContactDataEntity contactDataEntity);

}
