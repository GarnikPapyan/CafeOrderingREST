package org.example.cafe.cafeorderingsystem.repository;

import org.example.cafe.cafeorderingsystem.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing,Long> {
}
