package com.koskou.demo.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "product")
public class Product {

    @Id
    private String id;

    private String description;

    private int price;

    private int stock;

    private String status;

    @CreatedDate
    private Date createdDate;
}
