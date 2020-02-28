package com.koskou.demo.controller.stripe;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChargeResponse {

    private String id;
    private Long amount;
    private String balance_transaction;
    private boolean captured;
    private Long created;
    private String currency;
    private String description;
    private boolean livemode;
    private boolean paid;
    private String payment_method;
    private String receipt_url;
    private String status;
}
