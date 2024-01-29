package com.xeridia.service;

import com.xeridia.model.Hat;
import com.xeridia.model.Person;
import com.xeridia.restclient.HatClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {
    private final Map<Long, Person> personMap;

    private final HatClient hatClient;

    public PersonService(HatClient hatClient) {
        this.personMap = new HashMap<>();
        this.hatClient = hatClient;
    }
    public Optional<Person> findPersonById(Long id) {
        if(personMap.containsKey(id)) {
            return Optional.of(setHatDetails(personMap.get(id)));
        } else {
            return Optional.empty();
        }
    }

    public Collection<Person> findAll() {
        return personMap.values().stream().map(this::setHatDetails).collect(Collectors.toList());
    }

    public Person addPerson(Person person) {
        personMap.put(person.getId(), person);
        return person;
    }

    private Person setHatDetails(Person person) {
        person.setHat(getHatDetails(person.getHat().id()));
        return person;
    }

    private Hat getHatDetails(Long hatId) {
        try {
            return hatClient.findHatById(hatId);
        } catch (FeignException e) {
            log.error("Error while retrieving hat information", e);
            return new Hat(hatId, null, 0, null);
        }

    }
}
