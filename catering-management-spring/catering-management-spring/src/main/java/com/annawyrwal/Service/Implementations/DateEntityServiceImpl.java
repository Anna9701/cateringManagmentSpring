package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.DateEntityService;
import com.annawyrwal.model.DatesEntity;
import com.annawyrwal.repository.Interfaces.DatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DateEntityServiceImpl implements DateEntityService {
    private DatesRepository datesRepository;

    @Autowired
    public DateEntityServiceImpl(DatesRepository datesRepository) {
        this.datesRepository = datesRepository;
    }

    @Transactional
    @Override
    public void addDateEntity(DatesEntity dateEntity) {
        datesRepository.addDateEntity(dateEntity);
    }

    @Transactional
    @Override
    public List<DatesEntity> getAllDatesEntities() {
        return datesRepository.getAllDatesEntities();
    }

    @Transactional
    @Override
    public void deleteDateEntity(Integer dateId) {
        datesRepository.deleteDateEntity(dateId);
    }

    @Transactional
    @Override
    public DatesEntity updateDateEntity(DatesEntity dateEntity) {
        return datesRepository.updateDateEntity(dateEntity);
    }

    @Transactional
    @Override
    public DatesEntity getDateEntity(int dateId) {
        return datesRepository.getDateEntity(dateId);
    }
}
