
package com.paystream.kafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
 private final KafkaTemplate<String,String> kafka;
 public Producer(KafkaTemplate<String,String> kafka){ this.kafka=kafka; }
 public void publish(String msg){ kafka.send("payments",msg); }
}
