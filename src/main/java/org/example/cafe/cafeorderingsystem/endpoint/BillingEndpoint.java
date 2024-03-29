package org.example.cafe.cafeorderingsystem.endpoint;

import org.example.cafe.cafeorderingsystem.entity.Billing;
import org.example.cafe.cafeorderingsystem.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingEndpoint {

    @Autowired
    private BillingService billingService;

    @GetMapping("/generate/{id}")
    public ResponseEntity<Billing> viewOrder(@PathVariable("id")Long id){
        if(billingService.generateBilling(id)!=null){
            return ResponseEntity.ok(billingService.generateBilling(id));
        }
        return ResponseEntity.notFound().build();
    }

}
