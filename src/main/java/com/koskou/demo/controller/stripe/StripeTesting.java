package com.koskou.demo.controller.stripe;

import com.koskou.demo.servicedto.AddStripePayment;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/stripe")
public class StripeTesting {

    private StripeClient stripeClient;

    @Autowired
    public StripeTesting(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    // Set your secret key. Remember to switch to your live secret key in production!
    // See your keys here: https://dashboard.stripe.com/account/apikeys
    @GetMapping(
            value = "/test",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public void payment() throws Exception{
        Stripe.apiKey = "sk_test_vzODNB0cHOJ90KUJ2bGfWBCv";

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("amount", 1000);
        params.put("currency", "usd");
        ArrayList paymentMethodTypes = new ArrayList();
        paymentMethodTypes.add("card");
        params.put("payment_method_types", paymentMethodTypes);
        params.put("receipt_email", "jenny.rosen@example.com");

        try {
            PaymentIntent.create(params);
        }catch (Exception e){
            System.out.println("NOthing");
        }
    }

    public Token card() throws Exception{
        Stripe.apiKey = "sk_test_vzODNB0cHOJ90KUJ2bGfWBCv";

        Map<String, Object> card = new HashMap<String, Object>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 2);
        card.put("exp_year", 2021);
        card.put("cvc", "314");
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);

        Token token = Token.create(params);
        return token;
    }

    @PostMapping(
            value = "/charge",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ChargeResponse chargeCard(@RequestBody AddStripePayment addStripePayment) throws Exception {
        addStripePayment.setToken(card().getId());

        Charge charge = this.stripeClient.chargeCreditCard(addStripePayment);
        return toChargeResponse(charge);
    }

    private ChargeResponse toChargeResponse(Charge charge){
        ChargeResponse chargeResponse = ChargeResponse.builder()
                .id(charge.getId())
                .amount(charge.getAmount())
                .balance_transaction(charge.getBalanceTransaction())
                .captured(charge.getCaptured())
                .created(charge.getCreated())
                .currency(charge.getCurrency())
                .description(charge.getDescription())
                .livemode(charge.getLivemode())
                .paid(charge.getPaid())
                .payment_method(charge.getPaymentMethod())
                .receipt_url(charge.getReceiptUrl())
                .status(charge.getStatus())
                .build();
        return chargeResponse;
    }

}
