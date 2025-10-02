package com.quickbite_backend.quickbite_backend.service;

import com.quickbite_backend.quickbite_backend.model.MenuItem;
import com.quickbite_backend.quickbite_backend.model.Restaurant;
import com.quickbite_backend.quickbite_backend.repository.MenuItemRepository;
import com.quickbite_backend.quickbite_backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<MenuItem> getMenuItemsByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public MenuItem addMenuItem(Long restaurantId, MenuItem menuItem) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        menuItem.setRestaurant(restaurant);
        return menuItemRepository.save(menuItem);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        existing.setName(menuItem.getName());
        existing.setDescription(menuItem.getDescription());
        existing.setPrice(menuItem.getPrice());
        existing.setImageUrl(menuItem.getImageUrl());
        existing.setAvailable(menuItem.getAvailable());
        return menuItemRepository.save(existing);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
