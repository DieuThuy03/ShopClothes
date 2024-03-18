package com.example.shopclothes.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponse {

    private Long id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String password;
}
