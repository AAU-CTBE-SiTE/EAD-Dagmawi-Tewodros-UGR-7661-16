package com.craft.spring_boot_crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    // The restaurant menu, kept in memory in an ArrayList
    private final List<MenuItem> menu = new ArrayList<>(List.of(
            new MenuItem(1L, "Doro Wat", "Main", 250.0),
            new MenuItem(2L, "Kitfo", "Main", 300.0),
            new MenuItem(3L, "Sambusa", "Starter", 40.0),
            new MenuItem(4L, "Buna", "Drink", 30.0)
    ));

    // READ all (optional filter: /menu?category=Drink)
    @GetMapping
    public List<MenuItem> getMenu(@RequestParam(required = false) String category) {
        if (category == null) {
            return menu;
        }
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }
        return result;
    }

    // READ one (404 if missing, so the aspect's @AfterThrowing can be seen)
    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item " + id + " not found");
    }

    // CREATE
    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        menu.add(menuItem);
        return menuItem;
    }

    // UPDATE
    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem updated) {
        for (MenuItem item : menu) {
            if (item.getId().equals(id)) {
                item.setName(updated.getName());
                item.setCategory(updated.getCategory());
                item.setPrice(updated.getPrice());
                return item;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item " + id + " not found");
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteMenuItem(@PathVariable Long id) {
        boolean removed = menu.removeIf(item -> item.getId().equals(id));
        if (!removed) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item " + id + " not found");
        }
        return "Menu item deleted successfully";
    }
}
