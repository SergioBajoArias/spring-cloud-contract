package com.xeridia.controller;

import com.xeridia.model.Person;
import com.xeridia.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("person/{id}")
    public Person findPersonById(@PathVariable("id") Long id) {
        Person person = personService.findPersonById(id);
        if (person == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not Found"
            );
        }
        return person;
    }
}
