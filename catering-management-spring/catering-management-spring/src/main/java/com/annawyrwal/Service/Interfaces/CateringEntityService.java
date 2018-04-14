package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.CateringsEntity;

import java.util.List;

public interface CateringEntityService {
    CateringsEntity findCateringById(Integer cateringId);
    CateringsEntity findCateringByName(String name);
    List<CateringsEntity> getAllCateringsEntities();
    void deleteCateringEntity(Integer cateringId);
    CateringsEntity updateCateringEntity(CateringsEntity cateringsEntity);
    void addCateringEntity(CateringsEntity cateringsEntity);
}
