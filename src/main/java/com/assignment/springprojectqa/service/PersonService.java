package com.assignment.springprojectqa.service;

import com.assignment.springprojectqa.model.Person;
import org.springframework.stereotype.Service;
import com.assignment.springprojectqa.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements PersonServiceInterface<Person>{

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person create(Person createdPerson) {
        return this.personRepository.save(createdPerson);
    }

    @Override
    public List getAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        Optional <Person> optionalPerson = this.personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            return null;
        }
        return optionalPerson.get();
    }

    @Override
    public Person updateById(Long id, Person updatedPerson) {
        Optional <Person> optionalPerson = this.personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            return null;
        }
        Person retrievedPerson = optionalPerson.get();
        retrievedPerson.setFirstName(updatedPerson.getFirstName());
        retrievedPerson.setLastName(updatedPerson.getLastName());
        retrievedPerson.setEmail(updatedPerson.getEmail());
        retrievedPerson.setDateOfBirth(updatedPerson.getDateOfBirth());
        retrievedPerson.setGender(updatedPerson.getGender());
        return this.personRepository.save(retrievedPerson);
    }

    @Override
    public Person deleteById(Long id) {
        Optional <Person> optionalPerson = this.personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            return null;
        }
        this.personRepository.deleteById(id);
        return optionalPerson.get();
    }
}
