package service;

import model.Person;
import org.springframework.stereotype.Service;
import repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements PersonServiceInterface{

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
        return optionalPerson.orElse(null);
    }

    @Override
    public Person updateById(Long id, Person updatedPerson) {
        Optional <Person> optionalPerson = this.personRepository.findById(id);
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
        this.personRepository.deleteById(id);
        return optionalPerson.orElse(null);
    }
}
