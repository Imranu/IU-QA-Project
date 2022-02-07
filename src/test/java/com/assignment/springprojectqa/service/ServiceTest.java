package com.assignment.springprojectqa.service;

import com.assignment.springprojectqa.model.Person;
import com.assignment.springprojectqa.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.assignment.springprojectqa.enums.Gender.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@ActiveProfiles("test")
public class ServiceTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    Person newPerson = new Person( "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Person savedPerson = new Person(1L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    Person extraPerson = new Person(2L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
    Person updatedPerson = new Person(1L, "best", "test", "test@gmail.com", LocalDate.of(1999,10,23), FEMALE);
    Person deletedPerson = new Person(1L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);

    @Test
    public void testCreate() {

        Mockito.when(this.personRepository.save(newPerson)).thenReturn(savedPerson);

        assertEquals(savedPerson, this.personService.create(newPerson));

        Mockito.verify(this.personRepository, Mockito.times(1)).save(newPerson);

    }

    @Test
    public void testGetAll() {

        Mockito.when(this.personRepository.findAll()).thenReturn(List.of(newPerson, extraPerson));

        assertEquals(List.of(newPerson,extraPerson), this.personService.getAll());

        Mockito.verify(this.personRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void testGetAllEmpty() {

        Mockito.when(this.personRepository.findAll()).thenReturn(null);

        assertEquals(null, this.personService.getAll());

        Mockito.verify(this.personRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void testGetById() {

        Long validId = 1L;

        savedPerson.setId(validId);

        assertEquals(validId, savedPerson.getId());

        Optional<Person> optionalPerson = Optional.of(savedPerson);

        Mockito.when(this.personRepository.findById(validId)).thenReturn(optionalPerson);

        assertEquals(savedPerson, this.personService.getById(validId));

        Mockito.verify(this.personRepository, Mockito.times(1)).findById(validId);

    }

    @Test
    public void testGetByIdInvalid() {

        Long validId = 1L;

        savedPerson.setId(validId);

        assertEquals(validId, savedPerson.getId());

        Long invalidId = 541345L;

        Optional<Person> optionalPerson = Optional.of(savedPerson);

        Mockito.when(this.personRepository.findById(invalidId)).thenReturn(Optional.ofNullable(null));

        assertEquals(null, this.personService.getById(invalidId));

        Mockito.verify(this.personRepository, Mockito.times(1)).findById(invalidId);

    }

    @Test
    public void testUpdateById() {

        Long validId = 1L;

        savedPerson.setId(validId);

        assertEquals(validId, savedPerson.getId());

        Optional<Person> optionalPerson = Optional.of(updatedPerson);

        Mockito.when(this.personRepository.findById(validId)).thenReturn(optionalPerson);
        Mockito.when(this.personRepository.save(optionalPerson.get())).thenReturn(optionalPerson.get());

        assertEquals(updatedPerson, this.personService.updateById(validId, extraPerson));

        Mockito.verify(this.personRepository, Mockito.times(1)).findById(validId);
        Mockito.verify(this.personRepository, Mockito.times(1)).save(optionalPerson.get());

    }

    @Test
    public void testUpdateByIdInvalid() {

        Long validId = 1L;

        savedPerson.setId(validId);

        assertEquals(validId, savedPerson.getId());

        Long invalidId = 541345L;

        Optional<Person> optionalPerson = Optional.of(updatedPerson);

        Mockito.when(this.personRepository.findById(invalidId)).thenReturn(Optional.ofNullable(null));

        assertNull(this.personService.updateById(invalidId, extraPerson));

        Mockito.verify(this.personRepository, Mockito.times(1)).findById(invalidId);

    }

    @Test
    public void testDeleteById() {

        Long validId = 1L;

        savedPerson.setId(validId);

        assertEquals(validId, savedPerson.getId());

        Mockito.when(this.personRepository.findById(validId)).thenReturn(Optional.ofNullable(savedPerson));

        assertEquals(deletedPerson, this.personService.deleteById(validId));

        Mockito.verify(this.personRepository, Mockito.times(1)).findById(validId);
        Mockito.verify(this.personRepository, Mockito.times(1)).deleteById(validId);

    }

    @Test
    public void testDeleteByIdInvalid() {

        Long validId = 1L;

        savedPerson.setId(validId);

        assertEquals(validId, savedPerson.getId());

        Long invalidId = 541345L;

        Mockito.when(this.personRepository.findById(invalidId)).thenReturn(Optional.ofNullable(null));

        assertEquals(null, this.personService.deleteById(invalidId));

        Mockito.verify(this.personRepository, Mockito.times(1)).findById(invalidId);
        Mockito.verify(this.personRepository, Mockito.times(1)).deleteById(invalidId);

    }

}
