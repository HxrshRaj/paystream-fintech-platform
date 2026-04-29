
package com.paystream.repository;
import com.paystream.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<Payment,Long>{}
