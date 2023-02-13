package com.example.apigateway.services;

import com.example.apigateway.dtos.PersonDto;
import com.example.apigateway.exceptions.ResourceNotFoundException;
import com.example.apigateway.mapper.ModelMapperConverter;
import com.example.apigateway.models.Person;
import com.example.apigateway.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

//Faz com que possa ser injetado em outras classes em Runtime.
@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        logger.info("Finding all people!");

        return personRepository.findAll();
    }

    public Person findById(Long id) {
        logger.info("Finding a Person");

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
    }

    public PersonDto create(PersonDto person) {
        logger.info("Createing some person");

        var entity = ModelMapperConverter.parseObject(person, Person.class);

        personRepository.save(entity);

        return ModelMapperConverter.parseObject(entity, PersonDto.class);
    }

    public PersonDto update(PersonDto person) {
        logger.info("Updating some person");

        Person personEntity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        personEntity.setAddress(person.getAddress());
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setGender(person.getGender());

        personRepository.save(personEntity);

        return ModelMapperConverter.parseObject(personEntity, PersonDto.class);
    }

    public void delete(Long id) {
        logger.info("Deleting some person");

        Person personEntity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

        personRepository.delete(personEntity);
    }

}
