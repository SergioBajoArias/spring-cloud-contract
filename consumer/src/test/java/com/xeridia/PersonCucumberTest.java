package com.xeridia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xeridia.model.Hat;
import com.xeridia.model.Person;
import com.xeridia.service.PersonService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@CucumberOptions(
        plugin = "pretty",
        features = "src/test/resources"
)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@SpringBootTest(classes = ConsumerApplication.class)
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.xeridia:producer:+:stubs:8081")
public class PersonCucumberTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    private MvcResult mvcResult;

    @When("Person with identifier {long}, name {string}, age {int} and hat with identifier {long} is persisted")
    public void person_is_persisted(long personId, String name, int age, long hatId) {
        Person toPersistPerson = new Person(personId, name, age, new Hat(hatId, null, 0, null));
        personService.addPerson(toPersistPerson);
    }

    @And("Client requests person {long} details")
    public void the_client_requests_person_ID_details(long personId) throws Exception {
        mvcResult = mockMvc.perform(get("/people/{id}", personId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Then("Client gets a {int} http status")
    public void the_client_requests_person_ID_details(int expectedHttpStatus) {
        Assertions.assertEquals(expectedHttpStatus, mvcResult.getResponse().getStatus());
    }

    @And("Client gets a person with identifier {long}, name {string}, age {int} and hat with identifier {long}, name {string}, size {int} and color {string}")
    public void the_client_requests_person_ID_details(long personId, String name, int age, long hatId, String hatName, int hatSize, String hatColor) throws UnsupportedEncodingException, JsonProcessingException {
        Person expectedPerson = new Person(personId, name, age, new Hat(hatId, hatName, hatSize, hatColor));
        Person persistedPerson =  new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Person.class);
        Assertions.assertEquals(expectedPerson, persistedPerson);
    }

}
