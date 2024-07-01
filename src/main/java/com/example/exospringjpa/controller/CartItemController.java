package com.example.exospringjpa.controller;

import com.example.exospringjpa.model.CartItem;
import com.example.exospringjpa.service.CartItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public String listCartItems(Model model) {
        model.addAttribute("cartItems", cartItemService.getAllCartItems());
        return "cartItem/cartItem_list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("cartItem", new CartItem());
        return "cartItem/cartItem_form";
    }

    @PostMapping
    public String createCartItem(@ModelAttribute CartItem cartItem) {
        cartItemService.saveCartItem(cartItem);
        return "redirect:/cart-items";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        if (cartItem != null) {
            model.addAttribute("cartItem", cartItem);
            return "cartItem/cartItem_form";
        }
        return "redirect:/cart-items";
    }

    @PostMapping("/update/{id}")
    public String updateCartItem(@PathVariable("id") UUID id, @ModelAttribute CartItem cartItem) {
        cartItemService.updateCartItem(id, cartItem);
        return "redirect:/cart-items";
    }

    @PostMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable("id") UUID id) {
        cartItemService.deleteCartItem(id);
        return "redirect:/cart-items";
    }
}