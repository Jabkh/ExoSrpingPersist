package com.example.exospringjpa.repository;

import com.example.exospringjpa.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
}