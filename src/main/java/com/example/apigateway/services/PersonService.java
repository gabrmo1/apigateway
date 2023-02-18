package com.example.apigateway.services;

import com.example.apigateway.controllers.PersonController;
import com.example.apigateway.dtos.PersonDto;
import com.example.apigateway.exceptions.ResourceNotFoundException;
import com.example.apigateway.mapper.ModelMapperConverter;
import com.example.apigateway.models.Person;
import com.example.apigateway.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Faz com que possa ser injetado em outras classes em Runtime.
@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDto> findAll() {
        logger.info("Finding all people!");

        List<Person> people = personRepository.findAll();
        List<PersonDto> peopleDto = ModelMapperConverter.parseListObjects(people, PersonDto.class);

        peopleDto.forEach(this::implementFindByIdHATEOAS);

        return peopleDto;
    }

    public PersonDto findById(Long id) {
        logger.info("Finding a Person");

        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        PersonDto personDto = ModelMapperConverter.parseObject(person, PersonDto.class);

        implementFindByIdHATEOAS(personDto);

        return personDto;
    }

    public PersonDto create(PersonDto personDto) {
        logger.info("Creating some person");

        Person person = ModelMapperConverter.parseObject(personDto, Person.class);

        personRepository.save(person);

        PersonDto personDtoPostSave = ModelMapperConverter.parseObject(person, PersonDto.class);

        implementFindByIdHATEOAS(personDtoPostSave);

        return personDtoPostSave;
    }

    public PersonDto update(PersonDto personDto) {
        logger.info("Updating some person");

        Person person = personRepository.findById(personDto.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        person.setAddress(personDto.getAddress());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setGender(personDto.getGender());

        personRepository.save(person);

        implementFindByIdHATEOAS(personDto);

        return personDto;
    }

    public void delete(Long id) {
        logger.info("Deleting some person");

        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        personRepository.delete(person);
    }

    public PersonDto implementFindByIdHATEOAS(PersonDto personDto) {
        return personDto.add(linkTo(methodOn(PersonController.class).findById(personDto.getId())).withSelfRel().withTitle("finds by id attribute"));
    }

}
