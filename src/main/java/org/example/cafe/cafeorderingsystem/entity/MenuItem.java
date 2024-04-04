package org.example.cafe.cafeorderingsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_item")
@Data
@Builder
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;
    @NotEmpty(message = "Empty name")
    @Size(min = 1, max = 55)
    @Column(name = "name", nullable = false,length = 55)
    private String name;
    @NotEmpty(message = "Empty description")
    @Size(min = 1, max = 55)
    @Column(name = "description", nullable = false, length = 55)
    private String description;
    @NotEmpty(message = "Empty category")
    @Size(min = 1, max = 55)
    @Column(name = "category", nullable = false, length = 55)
    private String category;
    @Min(value = 1)
    @Column(name = "price",nullable = false)
    private Double price;


    public MenuItem() {
    }

    public MenuItem(String name, String description, String category, Double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
