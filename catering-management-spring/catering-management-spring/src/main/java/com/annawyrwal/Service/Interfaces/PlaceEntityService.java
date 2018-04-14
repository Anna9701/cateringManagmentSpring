package com.annawyrwal.Service.Interfaces;

import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.PlacesEntity;

import java.util.List;

public interface PlaceEntityService {
    void addPlaceEntity(PlacesEntity placeEntity);
    List<PlacesEntity> getAllPlaceEntity();
    void deletePlaceEntity(Integer placeId);
    PlacesEntity updatePlaceEntity(PlacesEntity placeEntity);
    PlacesEntity getPlaceEntity (int placeId);
    List<PlacesEntity> getPlaceEntityByClientsEntity(ClientsEntity clientsEntity);
}
