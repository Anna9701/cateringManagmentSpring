package com.annawyrwal.Service.Implementations;

import com.annawyrwal.Service.Interfaces.PlaceEntityService;
import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.PlacesEntity;
import com.annawyrwal.repository.Interfaces.PlacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceEntityServiceImpl implements PlaceEntityService {
    private PlacesRepository placesRepository;

    @Autowired
    public PlaceEntityServiceImpl(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    @Override
    public void addPlaceEntity(PlacesEntity placeEntity) {
        placesRepository.addPlaceEntity(placeEntity);
    }

    @Override
    public List<PlacesEntity> getAllPlaceEntity() {
        return placesRepository.getAllPlaceEntity();
    }

    @Override
    public void deletePlaceEntity(Integer placeId) {
        placesRepository.deletePlaceEntity(placeId);
    }

    @Override
    public PlacesEntity updatePlaceEntity(PlacesEntity placeEntity) {
        return placesRepository.updatePlaceEntity(placeEntity);
    }

    @Override
    public PlacesEntity getPlaceEntity(int placeId) {
        return placesRepository.getPlaceEntity(placeId);
    }

    @Override
    public List<PlacesEntity> getPlaceEntityByClientsEntity(ClientsEntity clientsEntity) {
        return placesRepository.getPlaceEntityByClientsEntity(clientsEntity);
    }
}
