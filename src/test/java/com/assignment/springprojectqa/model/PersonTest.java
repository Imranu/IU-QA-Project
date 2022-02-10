package com.assignment.springprojectqa.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static com.assignment.springprojectqa.enums.Gender.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class PersonTest {

    private Person testPerson = new Person(1L, "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);

    @Test
    public void testNoArgsConstructor() {
        Person noArgsPerson = new Person();
        assertEquals(null, noArgsPerson.getId());
        assertEquals(null, noArgsPerson.getFirstName());
        assertEquals(null, noArgsPerson.getLastName());
        assertEquals(null, noArgsPerson.getEmail());
        assertEquals(null, noArgsPerson.getGender());
    }

    @Test
    public void testCustomArgsConstructor() {
        Person testPerson = new Person( "test", "person", "test@mail.com", LocalDate.of(2000,01,01), MALE);
    assertEquals(null, testPerson.getId());
    assertEquals("test", testPerson.getFirstName());
    assertEquals("person", testPerson.getLastName());
    assertEquals("test@mail.com", testPerson.getEmail());
    assertEquals(LocalDate.of(2000,01,01), testPerson.getDateOfBirth());
    assertEquals(MALE, testPerson.getGender());
    assertEquals(Person.class, testPerson.getClass());
    }

    @Test
    public void testToString() {
        assertEquals("Person(id=1, firstName=test, lastName=person, email=test@mail.com, dateOfBirth=2000-01-01, gender=MALE)", testPerson.toString());
    }

    @Test
    public void testGettersAndSetters() {

        testPerson.setId(2L);
        testPerson.setFirstName("best");
        testPerson.setLastName("test");
        testPerson.setEmail("test@gmail.com");
        testPerson.setDateOfBirth(LocalDate.of(1999,10,23));
        testPerson.setGender(FEMALE);

        assertEquals(2L, testPerson.getId());
        assertEquals("best", testPerson.getFirstName());
        assertEquals("test", testPerson.getLastName());
        assertEquals("test@gmail.com", testPerson.getEmail());
        assertEquals(LocalDate.of(1999,10,23), testPerson.getDateOfBirth());
        assertEquals(FEMALE, testPerson.getGender());

    }

    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Person.class).verify();
    }


}
