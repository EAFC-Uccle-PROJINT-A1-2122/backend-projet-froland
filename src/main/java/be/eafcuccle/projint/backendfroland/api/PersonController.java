package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Person;
import be.eafcuccle.projint.backendfroland.persistence.PersonRepository;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
  private final PersonRepository personRepository;

  public PersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @GetMapping
  public List<PersonTO> allPeople() {
    List<Person> people = personRepository.findAll();
    return people.stream().map(PersonController::convertEntity).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonTO> personDetails(@PathVariable Long personId) {
    Person person = personRepository.getById(personId);
    PersonTO personTO = convertEntity(person);
    return ResponseEntity.ok().eTag(Long.toString(person.getVersion())).body(personTO);
  }

  @PostMapping
  public ResponseEntity<?> createPerson(@Valid @RequestBody PersonTO personTO,
      UriComponentsBuilder uriBuilder) {
    Person person = convertTO(personTO);
    Long id = personRepository.save(person).getId();
    URI newPersonUri = uriBuilder.path("/api/v1/people/{id}").build(id);
    return ResponseEntity.created(newPersonUri).build();
  }

  private static Person convertTO(PersonTO personTO) {
    Person person = new Person(personTO.getFirstName(), personTO.getLastName());
    person.setEmailAddress(personTO.getEmailAddres());
    person.setPhoneNumber(personTO.getPhoneNumber());
    return person;
  }

  private static PersonTO convertEntity(Person person) {
    return new PersonTO(person.getId(), person.getFirstName(), person.getLastName(),
        person.getEmailAddress(), person.getPhoneNumber());
  }
}
