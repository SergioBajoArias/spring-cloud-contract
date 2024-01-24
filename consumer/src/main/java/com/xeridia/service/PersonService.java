package com.xeridia.service;

import com.xeridia.model.Hat;
import com.xeridia.model.Person;
import com.xeridia.restclient.HatClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PersonService {
    private final Map<Long, Person> personMap;

    private final HatClient hatClient;

    public PersonService(HatClient hatClient) {
        this.hatClient = hatClient;

        this.personMap = Map.of(
                1L, getPerson(1L, "Tom", 18, 1L),
                2L, getPerson(2L, "Jerry", 15, 2L)
        );
    }
    public Person findPersonById(Long id) {
        return personMap.get(id);
    }

    public Collection<Person> findAll() {
        return personMap.values();
    }

    private Person getPerson(Long id, String name, int age, Long hatId) {
        Hat hat = new Hat(hatId, null, 0, null);
        try {
            hat = hatClient.findHatById(hat.id());
        } catch (FeignException e) {
            log.error("Error while retrieving hat information", e);
        }
        return new Person(id, name, age, hat);
    }
}
