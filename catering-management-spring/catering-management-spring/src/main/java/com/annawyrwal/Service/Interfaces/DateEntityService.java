package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.DatesEntity;

import java.util.List;

public interface DateEntityService {
    void addDateEntity(DatesEntity dateEntity);
    List<DatesEntity> getAllDatesEntities();
    void deleteDateEntity(Integer dateId);
    DatesEntity updateDateEntity(DatesEntity dateEntity);
    DatesEntity getDateEntity (int dateId);
}
