package service;

import java.util.List;

public interface PersonServiceInterface<Person> {

    Person create(Person createdPerson);

    List<Person> getAll();

    Person getById(Long id);

    Person update(Long id, Person updatedPerson);

    Person deleteById(Long id);

}
