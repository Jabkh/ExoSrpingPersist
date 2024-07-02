package com.example.exospringjpa.controller;

import com.example.exospringjpa.model.Furniture;
import com.example.exospringjpa.service.FurnitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String listFurniture(Model model) {
        model.addAttribute("furniture", furnitureService.getAllFurniture());
        return "furniture/furniture_list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "furniture/furniture_form";
    }

    @PostMapping
    public String createFurniture(@ModelAttribute Furniture furniture) {
        furnitureService.saveFurniture(furniture);
        return "redirect:/furniture";
    }

    @PostMapping("/furniture/save")
    public String saveOrUpdateFurniture(@ModelAttribute Furniture furniture) {
        if (furniture.getId() == null) {
            furnitureService.saveFurniture(furniture);
        } else {
            furnitureService.updateFurniture(furniture.getId(), furniture);
        }
        return "redirect:/furniture";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") UUID id, Model model) {
        Furniture furniture = furnitureService.getFurnitureById(id);
        if (furniture != null) {
            model.addAttribute("furniture", furniture);
            return "furniture/furniture_form";
        }
        return "redirect:/furniture";
    }

    @PostMapping("/update/{id}")
    public String updateFurniture(@PathVariable("id") UUID id, @ModelAttribute Furniture furniture) {
        furnitureService.updateFurniture(id, furniture);
        return "redirect:/furniture";
    }

    @PostMapping("/delete/{id}")
    public String deleteFurniture(@PathVariable("id") UUID id) {
        furnitureService.deleteFurniture(id);
        return "redirect:/furniture";
    }
}