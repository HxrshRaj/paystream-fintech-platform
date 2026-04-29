
package com.paystream.idempotency;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdempotencyService {
 private final ConcurrentHashMap<String, Boolean> store = new ConcurrentHashMap<>();

 public boolean exists(String key){
  return store.containsKey(key);
 }

 public void save(String key){
  store.put(key,true);
 }
}
