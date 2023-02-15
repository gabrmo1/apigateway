package com.example.apigateway.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
@Data
public class PersonDto {

    private long id;

    //    @JsonProperty("first_name")
    private String firstName;

    //    @JsonProperty("last_name")
    private String lastName;

    private String address;

    private String gender;

}
