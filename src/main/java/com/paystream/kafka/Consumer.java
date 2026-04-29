
package com.paystream.kafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

 @KafkaListener(topics="payments",groupId="group")
 public void consume(String msg){
  System.out.println("Consumed: "+msg);
  // simulate saga step
 }
}
