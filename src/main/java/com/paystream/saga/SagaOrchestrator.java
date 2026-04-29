
package com.paystream.saga;
import org.springframework.stereotype.Component;

@Component
public class SagaOrchestrator {

 public void handleSuccess(Long paymentId){
  System.out.println("Saga success for "+paymentId);
 }

 public void handleFailure(Long paymentId){
  System.out.println("Compensating transaction for "+paymentId);
 }
}
