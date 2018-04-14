package com.annawyrwal.repository.Interfaces;



import com.annawyrwal.model.DatesEntity;

import java.util.List;

public interface DatesRepository {
    void addDateEntity(DatesEntity dateEntity);
    List<DatesEntity> getAllDatesEntities();
    void deleteDateEntity(Integer dateId);
    DatesEntity updateDateEntity(DatesEntity dateEntity);
    DatesEntity getDateEntity (int dateId);
}
