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

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void updateCartItem(UUID id, CartItem updatedCartItem) {
        if (cartItemRepository.existsById(id)) {
            updatedCartItem.setId(id);
            cartItemRepository.save(updatedCartItem);
        }
    }

    public void deleteCartItem(UUID id) {
        cartItemRepository.deleteById(id);
    }
}