package com.example.exospringjpa.service;

import com.example.exospringjpa.model.Furniture;
import com.example.exospringjpa.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FurnitureService {

    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture getFurnitureById(UUID id) {
        return furnitureRepository.findById(id).orElse(null);
    }

    public void saveFurniture(Furniture furniture) {
        furnitureRepository.save(furniture);
    }

    public void updateFurniture(UUID id, Furniture updatedFurniture) {
        if (furnitureRepository.existsById(id)) {
            updatedFurniture.setId(id);
            furnitureRepository.save(updatedFurniture);
        }
    }

    public void deleteFurniture(UUID id) {
        furnitureRepository.deleteById(id);
    }

    public Furniture findByName(String name) {
        return furnitureRepository.findByName(name);
    }
}