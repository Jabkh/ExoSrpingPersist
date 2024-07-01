package com.example.exospringjpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "furniture_id", referencedColumnName = "id")
    private Furniture furniture;

    private int quantity;
}