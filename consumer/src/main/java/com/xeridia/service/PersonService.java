package com.xeridia.service;

import com.xeridia.model.Hat;
import com.xeridia.model.Person;
import com.xeridia.restclient.HatClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {
    private final Map<Long, Person> personMap;

    private final HatClient hatClient;

    public PersonService(HatClient hatClient) {
        this.hatClient = hatClient;
        this.personMap = new HashMap<>();
        this.personMap.put(1L, new Person(1L, "Sergio", 18, hatClient.findHatById(1L)));
        this.personMap.put(2L, new Person(2L, "Bablo", 15, hatClient.findHatById(2L)));
    }
    public Person findPersonById(Long id) {
        return personMap.get(id);
    }
}
