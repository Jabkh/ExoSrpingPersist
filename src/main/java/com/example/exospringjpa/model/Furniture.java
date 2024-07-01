package com.example.exospringjpa.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "furniture")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String description;

    private double price;

    private int stock;

    @OneToOne(mappedBy = "furniture", cascade = CascadeType.ALL)
    private CartItem cartItem;
}