package org.example.cafe.cafeorderingsystem.service;

import org.example.cafe.cafeorderingsystem.entity.MenuItem;
import org.example.cafe.cafeorderingsystem.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItem> gerAllMenuItems(){
        return menuItemRepository.findAll();
    }

    public MenuItem save(MenuItem menuItem){
       return menuItemRepository.save(menuItem);
    }

    public MenuItem findById(Long id) {
        MenuItem menuItem = null;
        Optional<MenuItem> optional = menuItemRepository.findById(id);
        if(optional.isPresent()){
            menuItem = optional.get();
        }
        return menuItem;
    }

    public void deleteById(Long id) {
        menuItemRepository.deleteById(id);
    }

    public boolean existsById(Long id){
        return menuItemRepository.existsById(id);
    }


}
