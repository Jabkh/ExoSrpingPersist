package com.example.exospringjpa.controller;

import com.example.exospringjpa.model.CartItem;
import com.example.exospringjpa.model.Furniture;
import com.example.exospringjpa.service.CartItemService;
import com.example.exospringjpa.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;
    private final FurnitureService furnitureService;

    public CartItemController(CartItemService cartItemService, FurnitureService furnitureService) {
        this.cartItemService = cartItemService;
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String viewCart(Model model) {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cart/cart";
    }

    @PostMapping("/add/{furnitureId}")
    public String addToCart(@PathVariable("furnitureId") UUID furnitureId,
                            @RequestParam("quantity") int quantity) {
        Furniture furniture = furnitureService.getFurnitureById(furnitureId);
        if (furniture != null && furniture.getStock() >= quantity) {
            CartItem cartItem = cartItemService.findByFurnitureId(furnitureId);
            if (cartItem != null) {
                // CartItem already exists, update quantity
                int oldQuantity = cartItem.getQuantity();
                int newQuantity = oldQuantity + quantity;
                cartItem.setQuantity(newQuantity);
                cartItemService.saveCartItem(cartItem);
            } else {
                // CartItem does not exist, create new
                cartItem = new CartItem();
                cartItem.addFurniture(furniture); // Ajoute la furniture à CartItem
                cartItem.setQuantity(quantity);
                // Copier les propriétés de Furniture
                cartItem.setName(furniture.getName());
                cartItem.setDescription(furniture.getDescription());
                cartItem.setPrice(furniture.getPrice());
                cartItemService.addToCart(cartItem);
            }

            // Update furniture stock
            furniture.setStock(furniture.getStock() - quantity);
            furnitureService.saveFurniture(furniture);
        }
        return "redirect:/cart";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") UUID id, @RequestParam("quantity") int quantity) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem != null) {
            int oldQuantity = cartItem.getQuantity();
            int diff = quantity - oldQuantity;

            // Adjust furniture stock based on quantity change
            List<Furniture> furnitureList = cartItem.getFurniture();
            for (Furniture furniture : furnitureList) {
                furniture.setStock(furniture.getStock() + diff);
                furnitureService.saveFurniture(furniture);
            }

            // Update cart item quantity
            cartItem.setQuantity(quantity);
            cartItemService.saveCartItem(cartItem);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") UUID id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem != null) {
            List<Furniture> furnitureList = cartItem.getFurniture();
            for (Furniture furniture : furnitureList) {
                furniture.setStock(furniture.getStock() + cartItem.getQuantity());
                furnitureService.saveFurniture(furniture);
            }
            cartItemService.removeFromCart(id);
        }
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        for (CartItem cartItem : cartItems) {
            List<Furniture> furnitureList = cartItem.getFurniture();
            for (Furniture furniture : furnitureList) {
                furniture.setStock(furniture.getStock() + cartItem.getQuantity());
                furnitureService.saveFurniture(furniture);
            }
        }
        cartItemService.clearCart();
        return "redirect:/cart";
    }
}

