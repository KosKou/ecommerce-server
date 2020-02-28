package com.koskou.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "user")
public class User {

    @Id
    private String id;
    @Email
    private String email;

    private String username;

    @JsonIgnore
    private String password;

    private String status;

    private List<String> roles;

    @CreatedDate
    private Date createdDate;

}
