package com.example.exospringjpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList; // Importer ArrayList pour l'initialisation
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "cartItem", cascade = CascadeType.DETACH, orphanRemoval = true)
    private List<Furniture> furniture = new ArrayList<>(); // Initialisation de la liste

    private int quantity;

    private String name;
    private String description;
    private double price;

    // Méthodes pour ajouter et retirer Furniture
    public void addFurniture(Furniture furniture) {
        if (this.furniture == null) {
            this.furniture = new ArrayList<>(); // Initialiser si null, par précaution
        }
        this.furniture.add(furniture);
        furniture.setCartItem(this);
    }

    public void removeFurniture(Furniture furniture) {
        if (this.furniture != null) {
            this.furniture.remove(furniture);
            furniture.setCartItem(null);
        }
    }
}
