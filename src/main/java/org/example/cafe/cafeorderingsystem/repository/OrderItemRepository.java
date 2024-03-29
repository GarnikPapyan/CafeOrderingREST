package org.example.cafe.cafeorderingsystem.repository;

import org.example.cafe.cafeorderingsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

}
