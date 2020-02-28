package com.koskou.demo.controller.stripe;

import com.koskou.demo.servicedto.AddStripePayment;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StripeClient {

    @Autowired
    StripeClient(){
        Stripe.apiKey = "sk_test_vzODNB0cHOJ90KUJ2bGfWBCv";
    }

    public Charge chargeCreditCard(AddStripePayment addStripePayment) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(addStripePayment.getAmount() * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", addStripePayment.getToken());
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    public Customer createCustomer(String token, String email) throws Exception{
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }

    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = Customer.retrieve(customerId).getDefaultSource(); //token
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);

        return charge;
    }
}
