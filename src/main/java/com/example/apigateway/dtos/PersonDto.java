package com.example.apigateway.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
@Data
public class PersonDto extends RepresentationModel<PersonDto> implements Serializable {
    private long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

}
