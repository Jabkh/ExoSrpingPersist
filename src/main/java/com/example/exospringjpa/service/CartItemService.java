package com.example.exospringjpa.service;

import com.example.exospringjpa.model.CartItem;
import com.example.exospringjpa.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(UUID id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    public void addToCart(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void removeFromCart(UUID id) {
        cartItemRepository.deleteById(id);
    }

    public CartItem findByFurnitureId(UUID furnitureId) {
        return cartItemRepository.findByFurnitureId(furnitureId);
    }

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}