package com.koskou.demo.entity;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "offer")
public class Offer {

    @Id
    private String id;

    @URL
    private String url;

    private String status;
}
