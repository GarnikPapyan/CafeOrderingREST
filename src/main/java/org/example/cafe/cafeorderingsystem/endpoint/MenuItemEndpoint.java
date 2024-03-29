package org.example.cafe.cafeorderingsystem.endpoint;

import org.example.cafe.cafeorderingsystem.entity.MenuItem;
import org.example.cafe.cafeorderingsystem.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuItemEndpoint {

    @Autowired
    private  MenuItemService menuItemService;


    @GetMapping("/items")
    public List<MenuItem> getAll(){
        return menuItemService.gerAllMenuItems();
    }
    @PostMapping("/add")
    public ResponseEntity<MenuItem> create(@RequestBody MenuItem menuItem){
         return ResponseEntity.ok(menuItemService.save(menuItem));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MenuItem> update(@PathVariable("id")Long id, @RequestBody MenuItem menuItem){
        MenuItem byId = menuItemService.findById(id);
        if(byId!=null){
            byId.setCategory(menuItem.getCategory());
            byId.setDescription(menuItem.getDescription());
            byId.setName(menuItem.getName());
            byId.setPrice(menuItem.getPrice());
            return ResponseEntity.ok(menuItemService.save(byId));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        if(menuItemService.existsById(id)){
            menuItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
