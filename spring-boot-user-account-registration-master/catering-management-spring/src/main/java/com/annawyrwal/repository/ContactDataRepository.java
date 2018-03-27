package com.annawyrwal.repository;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.ContactDataEntity;
import org.springframework.data.repository.CrudRepository;

public interface ContactDataRepository extends CrudRepository<ContactDataEntity, Integer> {
    ContactDataEntity findByClientsByClientid(ClientsEntity clientsByClientid);
}
