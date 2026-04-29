
package com.paystream.dto;
import jakarta.validation.constraints.*;
public class PaymentRequest {
 @NotBlank public String userId;
 @Positive public double amount;
 @NotBlank public String idempotencyKey;
}
