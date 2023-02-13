package com.example.apigateway.dtos;

import lombok.Data;

@Data
public class PersonDto {

    private long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

}
