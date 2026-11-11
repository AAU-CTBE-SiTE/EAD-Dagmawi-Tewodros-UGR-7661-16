package com.craft.spring_boot_crud;

public class MenuItem {
    private Long id;
    private String name;
    private String category;
    private double price;

    public MenuItem() {
    }

    public MenuItem(Long id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Makes the aspect's log lines readable
    @Override
    public String toString() {
        return "MenuItem{id=" + id + ", name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}
