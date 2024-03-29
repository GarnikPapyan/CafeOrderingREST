package org.example.cafe.cafeorderingsystem.service;


import org.example.cafe.cafeorderingsystem.entity.OrderItem;
import org.example.cafe.cafeorderingsystem.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> gerAllOrderItems(){
        return orderItemRepository.findAll();
    }

    public void save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    public OrderItem getById(Long id) {
        OrderItem orderItem = null;
        Optional<OrderItem> optional = orderItemRepository.findById(id);
        if(optional.isPresent()){
            orderItem = optional.get();
        }
        return orderItem;
    }

    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }



}
