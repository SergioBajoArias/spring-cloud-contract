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

    @MockBean
    HatService hatService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(hatController);

        Map<Long, Hat> hats = Map.of(
                1L, new Hat(1L, "Test Hat 1", 10, "striped"),
                2L, new Hat(2L, "Test Hat 2", 7, "green")
        );

        Mockito.when(hatService.findAll()).thenReturn(hats.values());
        Mockito.when(hatService.findHatById(1L)).thenReturn(hats.get(1L));
        Mockito.when(hatService.findHatById(2L)).thenReturn(hats.get(2L));
    }
}
