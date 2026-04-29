
package com.paystream.entity;
import jakarta.persistence.*;

@Entity
public class Payment {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 public Long id;
 public String userId;
 public double amount;
 public String status;
}
