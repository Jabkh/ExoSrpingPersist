package com.example.exospringjpa.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "furniture")
public class Furniture {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private double price;

    @Setter
    @Getter
    private int stock;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    private CartItem cartItem;

    public @NotBlank(message = "Name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is mandatory") String name) {
        this.name = name;
    }

}