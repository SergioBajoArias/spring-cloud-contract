package com.xeridia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeridia.model.Hat;
import com.xeridia.model.Person;
import com.xeridia.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ConsumerApplication.class)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.xeridia:producer:+:stubs:8081")
public class PersonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @BeforeEach
    public void init() {
        personService.addPerson(new Person(1L, "Tom", 18, new Hat(1L, null, 0, null)));
        personService.addPerson(new Person(2L, "Jerry", 15, new Hat(2L, null, 0, null)));
    }

    private static Stream<Arguments> findByIdParameters() {
        return Stream.of(
                Arguments.of(new Person(1L, "Tom", 18, new Hat(1L, "Test Hat 1", 10, "striped"))),
                Arguments.of(new Person(2L, "Jerry", 15, new Hat(2L, "Test Hat 2", 7, "green")))
        );
    }

    @ParameterizedTest
    @MethodSource("findByIdParameters")
    public void findById(Person expectedPerson) throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/people/{id}", expectedPerson.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Person person = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Person.class);

        Assertions.assertEquals(expectedPerson, person);
    }

    @Test
    public void findAll() throws Exception {
        Collection<Person> expectedPeople = personService.findAll();

        MvcResult mvcResult = mockMvc.perform(get("/people")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Collection<Person> people = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

        Assertions.assertEquals(expectedPeople, people);
    }

    @Test
    public void findByIdNotFound() throws Exception {
        mockMvc.perform(get("/people/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
