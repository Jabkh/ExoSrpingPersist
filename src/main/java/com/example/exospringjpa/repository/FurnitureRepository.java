package com.example.exospringjpa.repository;

import com.example.exospringjpa.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FurnitureRepository extends JpaRepository<Furniture, UUID> {

    Furniture findByName(String name);
}