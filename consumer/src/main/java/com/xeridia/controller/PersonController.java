package com.xeridia.controller;

import com.xeridia.model.Person;
import com.xeridia.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable("id") Long id) {
        Optional<Person> person = personService.findPersonById(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not Found"
            );
        }
        return person.get();
    }

    @GetMapping
    public Collection<Person> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
}
