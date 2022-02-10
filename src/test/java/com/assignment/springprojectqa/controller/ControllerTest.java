package com.assignment.springprojectqa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import static com.assignment.springprojectqa.enums.Gender.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.*;
import java.time.LocalDate;
import java.util.List;
import com.assignment.springprojectqa.model.Person;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.RequestMatchResult;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:person-schema.sql", "classpath:person-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    Person newPerson = new Person( "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Person savedPerson = new Person(1L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Person extraPerson = new Person(3L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
    Person updatedPerson = new Person(4L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
    Person deletedPerson = new Person(4L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Long validId = 1L;
    Long invalidId = 541345L;

    @Test
    public void testCreate() throws Exception {

        String newPersonJSON = this.objectMapper.writeValueAsString(newPerson);
        RequestBuilder requestBuilder = post("/api/person").contentType(MediaType.APPLICATION_JSON).content(newPersonJSON);
        ResultMatcher resultMatcherStatus = status().isCreated();
        String savedPersonJSON = this.objectMapper.writeValueAsString(savedPerson);
        ResultMatcher resultMatcherBody = content().json(savedPersonJSON);

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

    @Test
    public void testGetAll() throws Exception {

        savedPerson = new Person(1L, "Michael", "Jackson", "MJSing@mail.com", LocalDate.of(1999,01,01), MALE);

        RequestBuilder requestBuilder = get("/api/person");

        String savedPersonJSON = this.objectMapper.writeValueAsString(List.of(savedPerson));
        ResultMatcher resultMatcherBody = content().json(savedPersonJSON);
        ResultMatcher resultMatcherStatus = status().isFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

//    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//    String content = result.getResponse().getContentAsString();
//
//    System.out.println(content);

    @Test
    public void testGetById() throws Exception {

        savedPerson = new Person(1L, "Michael", "Jackson", "MJSing@mail.com", LocalDate.of(1999,01,01), MALE);
        String peopleJSON = this.objectMapper.writeValueAsString(savedPerson);

        RequestBuilder requestBuilder = get("/api/person/" + validId);

        ResultMatcher resultMatcherBody = content().json(peopleJSON);
        ResultMatcher resultMatcherStatus = status().isFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

}
