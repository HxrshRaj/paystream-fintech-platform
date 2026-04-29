
package com.paystream.controller;

import com.paystream.dto.PaymentRequest;
import com.paystream.service.PaymentService;
import com.paystream.kafka.Producer;
import com.paystream.idempotency.IdempotencyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

 private final PaymentService service;
 private final Producer producer;
 private final IdempotencyService idem;

 public PaymentController(PaymentService service,Producer producer,IdempotencyService idem){
  this.service=service;
  this.producer=producer;
  this.idem=idem;
 }

 @PostMapping
 public String create(@Valid @RequestBody PaymentRequest req){
  if(idem.exists(req.idempotencyKey)){
   return "DUPLICATE_REQUEST";
  }
  idem.save(req.idempotencyKey);

  var p=service.create(req.userId,req.amount);
  producer.publish("PAYMENT_CREATED:"+p.id);
  return "SUCCESS:"+p.id;
 }
}
