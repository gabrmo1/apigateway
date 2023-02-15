package com.example.apigateway.controllers;

import com.example.apigateway.dtos.PersonDto;
import com.example.apigateway.mapper.ModelMapperConverter;
import com.example.apigateway.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    //O "produces" e o "consumes" estão presentes somente para que seja feita a documentação correta do swagger futuramente.
    @GetMapping(value = "{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDto findById(@PathVariable Long id) {
        return ModelMapperConverter.parseObject(personService.findById(id), PersonDto.class);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonDto> findall() {
        return ModelMapperConverter.parseListObjects(personService.findAll(), PersonDto.class);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDto createPerson(@RequestBody PersonDto person) {

        return personService.create(person);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonDto updatePerson(@RequestBody PersonDto person) {

        return personService.update(person);
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
