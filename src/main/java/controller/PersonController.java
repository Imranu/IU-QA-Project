package controller;

import model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //    Person create(Person createdPerson);
    @PostMapping
    public ResponseEntity create(@RequestBody Person person) {
        return new ResponseEntity(this.personService.create(person), HttpStatus.CREATED);
    }

    //    List<Person> getAll();
    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity<List<Person>>(this.personService.getAll(), HttpStatus.FOUND);
    }

    //    Person getById(Long id);
    @GetMapping(path = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Person returnValue = this.personService.getById(id);
        if (returnValue != null) {
            return new ResponseEntity<Person>(returnValue, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    //    Person updateById(Long id, Person updatedPerson);
    @PutMapping(path = "/{id}")
    public ResponseEntity updateById(@PathVariable Long id, @RequestBody Person person) {
        Person returnValue = this.personService.deleteById(id);
        if (returnValue != null) {
            return new ResponseEntity<>(returnValue, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

    //    Person deleteById(Long id);
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        Person returnValue = this.personService.deleteById(id);
        if (returnValue != null) {
            return new ResponseEntity(returnValue, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }
    }

}
