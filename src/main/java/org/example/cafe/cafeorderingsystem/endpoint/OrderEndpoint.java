package org.example.cafe.cafeorderingsystem.endpoint;


import org.example.cafe.cafeorderingsystem.entity.Order;
import org.example.cafe.cafeorderingsystem.service.BillingService;
import org.example.cafe.cafeorderingsystem.service.OrderItemService;
import org.example.cafe.cafeorderingsystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderEndpoint {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private BillingService billingService;

    @GetMapping("/view/{id}")
    public ResponseEntity<Order> view(@PathVariable("id") Long id){
        Optional<Order> byId = orderService.findById(id);
        if(byId.isPresent()){
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/history")
    public ResponseEntity<List<Order>> getAll(){
        return ResponseEntity.ok(orderService.gerAllOrders());
    }

    @PostMapping("/place")
    public ResponseEntity<Order> create(@RequestBody Order order){
        return ResponseEntity.ok(orderService.save(order));
    }


    @PutMapping("/modify/{id}")
    public ResponseEntity<Order> update(@PathVariable("id")Long id, @RequestBody Order order){
        Optional<Order> byId = orderService.findById(id);
        if(byId.isPresent()){
            Order orderUpdate = byId.get();
            orderUpdate.setItems(order.getItems());
            orderUpdate.setTableNumber(order.getTableNumber());
            orderUpdate.setWaiterId(order.getWaiterId());
            return ResponseEntity.ok(orderService.save(orderUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        if(orderService.existsById(id)){
            billingService.deleteById(id);
            orderService.deleteById(id);
            orderItemService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
