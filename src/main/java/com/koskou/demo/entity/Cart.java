package com.koskou.demo.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "cart")
public class Cart {

    @Id
    private String id;

    private List<Product> products;

    private String userId;

    private int totalPrice;

    private String status;

    @CreatedDate
    private Date createdDate;
}
