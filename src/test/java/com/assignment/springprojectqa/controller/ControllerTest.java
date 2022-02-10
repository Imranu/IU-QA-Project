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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.*;
import java.time.LocalDate;
import java.util.List;
import com.assignment.springprojectqa.model.Person;
import org.springframework.http.MediaType;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:person-schema.sql", "classpath:person-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreate() throws Exception {

        Person newPerson = new Person( "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
        Person savedPerson = new Person(2L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);

        String newPersonJSON = this.objectMapper.writeValueAsString(newPerson);
        RequestBuilder requestBuilder = post("/api/person").contentType(MediaType.APPLICATION_JSON).content(newPersonJSON);
        ResultMatcher resultMatcherStatus = status().isCreated();
        String savedPersonJSON = this.objectMapper.writeValueAsString(savedPerson);
        ResultMatcher resultMatcherBody = content().json(savedPersonJSON);

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

    @Test
    public void testGetAll() throws Exception {

        Person savedPerson = new Person(1L, "Michael", "Jackson", "MJSing@mail.com", LocalDate.of(1999,01,01), MALE);

        RequestBuilder requestBuilder = get("/api/person");

        String savedPersonJSON = this.objectMapper.writeValueAsString(List.of(savedPerson));
        ResultMatcher resultMatcherBody = content().json(savedPersonJSON);
        ResultMatcher resultMatcherStatus = status().isFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

    @Test
    public void testGetById() throws Exception {

        Long validId = 1L;

        Person savedPerson = new Person(1L, "Michael", "Jackson", "MJSing@mail.com", LocalDate.of(1999,01,01), MALE);
        String peopleJSON = this.objectMapper.writeValueAsString(savedPerson);

        RequestBuilder requestBuilder = get("/api/person/" + validId);

        ResultMatcher resultMatcherBody = content().json(peopleJSON);
        ResultMatcher resultMatcherStatus = status().isFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

    @Test
    public void testGetByIdInvalid() throws Exception {

        Long invalidId = 6546524L;

        RequestBuilder requestBuilder = get("/api/person/" + invalidId);

        ResultMatcher resultMatcherStatus = status().isNotFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus);

    }

    @Test
    public void UpdateGetById() throws Exception {

        Long validId = 1L;

        Person extraPerson = new Person( "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
        String extraPersonJSON = this.objectMapper.writeValueAsString(extraPerson);

        Person updatedPerson = new Person(1L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
        String updatedPersonJSON = this.objectMapper.writeValueAsString(updatedPerson);

        RequestBuilder requestBuilder = put("/api/person/" + validId).contentType(MediaType.APPLICATION_JSON).content(extraPersonJSON);

        ResultMatcher resultMatcherBody = content().json(updatedPersonJSON);
        ResultMatcher resultMatcherStatus = status().isAccepted();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }

    @Test
    public void UpdateGetByIdInvalid() throws Exception {

        Long invalidId = 6546524L;

        Person extraPerson = new Person( "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
        String extraPersonJSON = this.objectMapper.writeValueAsString(extraPerson);

        RequestBuilder requestBuilder = put("/api/person/" + invalidId).contentType(MediaType.APPLICATION_JSON).content(extraPersonJSON);

        ResultMatcher resultMatcherStatus = status().isNotFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus);

    }

    @Test
    public void DeleteById() throws Exception {

        Long validId = 1L;

        Person deletedPerson  = new Person(1L, "Michael", "Jackson", "MJSing@mail.com", LocalDate.of(1999,01,01), MALE);
        String deletedPersonJSON = this.objectMapper.writeValueAsString(deletedPerson);

        RequestBuilder requestBuilder = delete("/api/person/" + validId);

        ResultMatcher resultMatcherBody = content().json(deletedPersonJSON);
        ResultMatcher resultMatcherStatus = status().isAccepted();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus).andExpect(resultMatcherBody);

    }


    @Test
    public void DeleteByIdInvalid() throws Exception {


        Long invalidId = 6546524L;

        RequestBuilder requestBuilder = delete("/api/person/" + invalidId);

        ResultMatcher resultMatcherStatus = status().isNotFound();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus);

    }



}
