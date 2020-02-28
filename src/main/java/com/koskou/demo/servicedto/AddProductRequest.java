package com.koskou.demo.servicedto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductRequest {

    private String description;

    private int price;

    private int stock;
}
