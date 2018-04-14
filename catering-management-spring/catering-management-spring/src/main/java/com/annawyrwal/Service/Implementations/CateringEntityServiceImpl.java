package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.repository.Interfaces.CateringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CateringEntityServiceImpl implements CateringEntityService {
    private CateringRepository cateringRepository;

    @Autowired
    public CateringEntityServiceImpl(CateringRepository cateringRepository) {
        this.cateringRepository = cateringRepository;
    }

    @Transactional
    @Override
    public CateringsEntity findCateringById(Integer cateringId) {
        return cateringRepository.findById(cateringId);
    }

    @Transactional
    @Override
    public CateringsEntity findCateringByName(String name) {
        return cateringRepository.findByName(name);
    }

    @Transactional
    @Override
    public List<CateringsEntity> getAllCateringsEntities() {
        return cateringRepository.getAllCateringsEntities();
    }

    @Transactional
    @Override
    public void deleteCateringEntity(Integer cateringId) {
        cateringRepository.deleteCateringEntity(cateringId);
    }

    @Transactional
    @Override
    public CateringsEntity updateCateringEntity(CateringsEntity cateringsEntity) {
        return cateringRepository.updateCateringEntity(cateringsEntity);
    }

    @Transactional
    @Override
    public void addCateringEntity(CateringsEntity cateringsEntity) {
        cateringRepository.addCateringEntity(cateringsEntity);
    }
}
