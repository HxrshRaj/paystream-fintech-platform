
package com.paystream.service;

import com.paystream.entity.Payment;
import com.paystream.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

 private final PaymentRepository repo;

 public PaymentService(PaymentRepository repo){
  this.repo=repo;
 }

 public Payment create(String userId,double amount){
  Payment p=new Payment();
  p.userId=userId;
  p.amount=amount;
  p.status="INITIATED";
  return repo.save(p);
 }
}
