package com.koskou.demo.servicedto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStripePayment {

    private String token;

    private double amount;
}
