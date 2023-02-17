package com.example.apigateway.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
@Data
public class PersonDto {

    private long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

}
