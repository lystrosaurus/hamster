package com.hamster.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by opabinia on 2017/5/11.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTests {
//    @Autowired
//    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

//    @Test
//    public void testHello() {
//        String body = this.restTemplate.getForObject("/", String.class);
//        assertThat(body).isEqualTo("Hello World");
//    }

    @Test
    public void testIndex() throws Exception {
        MvcResult result = this.mvc.perform(get("/index").accept(MediaType.ALL)).andExpect(status().isOk()).andReturn();
        Assert.assertNotNull(result);
    }
}

