package com.quickbite_backend.quickbite_backend.controller;

import com.quickbite_backend.quickbite_backend.model.MenuItem;
import com.quickbite_backend.quickbite_backend.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItem> getMenuItemsByRestaurant(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurant(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}")
    public MenuItem addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItem menuItem) {
        return menuItemService.addMenuItem(restaurantId, menuItem);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) {
        return menuItemService.updateMenuItem(id, menuItem);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        menuItemService.deleteMenuItem(id);
    }
}
