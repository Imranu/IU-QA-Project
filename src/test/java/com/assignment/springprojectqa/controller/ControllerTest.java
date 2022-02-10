package com.assignment.springprojectqa.controller;

import com.assignment.springprojectqa.model.Person;
import com.assignment.springprojectqa.repository.PersonRepository;
import com.assignment.springprojectqa.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.assignment.springprojectqa.enums.Gender.FEMALE;
import static com.assignment.springprojectqa.enums.Gender.MALE;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:person-schema.sql", "classpath:person-data.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    Person newPerson = new Person( "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Person savedPerson = new Person(1L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Person extraPerson = new Person(2L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
    Person updatedPerson = new Person(1L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
    Person deletedPerson = new Person(1L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Long validId = 1L;
    Long invalidId = 541345L;

    @Test
    public void testCreate() {

//        Mockito.when(this.personService.create()).thenReturn()

    }

    @Test
    public void testGetAll() throws Exception {

        List<Person> people = new ArrayList<>();
        people.add(savedPerson);
        people.add(extraPerson);

        Mockito.when(this.personService.getAll()).thenReturn(people);

        String url = "/api/person";
        RequestBuilder requestBuilder = get("url");
        ResultMatcher resultMatcherStatus = status().isCreated();

        this.mockMvc.perform(requestBuilder).andExpect(resultMatcherStatus);

    }


}
