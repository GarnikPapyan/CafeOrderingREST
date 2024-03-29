package org.example.cafe.cafeorderingsystem.repository;

import org.example.cafe.cafeorderingsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
