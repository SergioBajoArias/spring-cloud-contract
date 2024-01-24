package com.xeridia.contract;

import com.xeridia.controller.HatController;
import com.xeridia.model.Hat;
import com.xeridia.service.HatService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

@SpringBootTest
public abstract class BaseClass {

    @Autowired
    HatController hatController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(hatController);
    }
}
