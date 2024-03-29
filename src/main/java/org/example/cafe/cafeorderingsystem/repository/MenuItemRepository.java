package org.example.cafe.cafeorderingsystem.repository;

import org.example.cafe.cafeorderingsystem.entity.MenuItem;
import org.example.cafe.cafeorderingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

}
