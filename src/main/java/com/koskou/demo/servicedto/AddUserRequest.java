package com.koskou.demo.servicedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddUserRequest {

    @Email
    private String email;
    @Size(min = 6)
    private String password;
    
}
