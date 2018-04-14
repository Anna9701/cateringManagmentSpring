package com.annawyrwal.repository.Interfaces;

import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.model.ClientsEntity;

import java.util.List;

public interface CateringRepository {
    CateringsEntity findById(Integer cateringId);
    CateringsEntity findByName(String name);
    List<CateringsEntity> getAllCateringsEntities();
    void deleteCateringEntity(Integer cateringId);
    CateringsEntity updateCateringEntity(CateringsEntity cateringsEntity);
    void addCateringEntity(CateringsEntity cateringsEntity);
    List<CateringsEntity> getCateringsEntitiesByClient(ClientsEntity clientsEntity);
}
