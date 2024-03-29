package org.example.cafe.cafeorderingsystem.service;

import org.example.cafe.cafeorderingsystem.entity.Billing;
import org.example.cafe.cafeorderingsystem.entity.MenuItem;
import org.example.cafe.cafeorderingsystem.entity.Order;
import org.example.cafe.cafeorderingsystem.entity.OrderItem;
import org.example.cafe.cafeorderingsystem.repository.BillingRepository;
import org.example.cafe.cafeorderingsystem.repository.MenuItemRepository;
import org.example.cafe.cafeorderingsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillingService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    private static final Double TAX = 0.2;
    private static final Double FEE = 0.1;
    private static final Double TIP = 0.1;
    private final BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public List<Billing> gerAllBillings(){
        return billingRepository.findAll();
    }

    public void save(Billing billing){
        billingRepository.save(billing);
    }

    public Billing getById(Long id) {
        Billing billing = null;
        Optional<Billing> optional = billingRepository.findById(id);
        if(optional.isPresent()){
            billing = optional.get();
        }
        return billing;
    }

    public void deleteById(Long id) {
        billingRepository.deleteById(id);
    }

    public Billing generateBilling(Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            return null;
        }
        Order order = optionalOrder.get();
        Optional<Billing> optional = billingRepository.findById(id);
        if(optional.isPresent()){
           return optional.get();
        }
        Billing billing = new Billing();
        billing.setOrder(order);
        List<OrderItem> orderItems = order.getItems();
        Integer quantity = orderItems.get(0).getQuantity();
        Optional<MenuItem> menuItem = menuItemRepository.findById(orderItems.get(0).getItemId());
        if(menuItem.isEmpty()){
            return null;
        }

        billing.setServiceFee(menuItem.get().getPrice()*FEE*quantity);
        billing.setTax(menuItem.get().getPrice()*TAX*quantity);
        billing.setTip(menuItem.get().getPrice()*TIP*quantity);
        billing.setTotal(menuItem.get().getPrice()*(TAX+FEE+TIP)+menuItem.get().getPrice()*quantity);

        return billingRepository.save(billing);
    }

}
