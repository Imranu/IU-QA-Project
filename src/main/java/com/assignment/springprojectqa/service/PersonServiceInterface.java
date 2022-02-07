package com.assignment.springprojectqa.service;

import com.assignment.springprojectqa.model.Person;
import java.util.List;

public interface PersonServiceInterface {

    Person create(Person createdPerson);

    List<Person> getAll();

    Person getById(Long id);

    Person updateById(Long id, Person updatedPerson);

    Person deleteById(Long id);

}
