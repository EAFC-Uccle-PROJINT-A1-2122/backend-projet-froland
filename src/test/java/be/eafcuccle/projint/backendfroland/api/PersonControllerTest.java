package be.eafcuccle.projint.backendfroland.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Person;
import be.eafcuccle.projint.backendfroland.persistence.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
  private static final String PERSON1_FIST_NAME = "Jim";
  private static final String PERSON1_LAST_NAME = "Greer";
  private static final String PERSON2_FIRST_NAME = "Ryan";
  private static final String PERSON2_LAST_NAME = "Harper";
  private static final PersonTO PERSON1_TO = new PersonTO(1L, PERSON1_FIST_NAME, PERSON1_LAST_NAME, "jim.greer@cia.gov.us", "+155512345");

  @Mock
  private PersonRepository personRepository;
  @InjectMocks
  private PersonController personController;
  @Spy
  private Person person1 = new Person(PERSON1_FIST_NAME, PERSON1_LAST_NAME);
  private Person person2 = new Person(PERSON2_FIRST_NAME, PERSON2_LAST_NAME);


  @Test
  public void allPeople() {
    when(personRepository.findAll()).thenReturn(List.of(person1, person2));

    List<PersonTO> allPeople = personController.allPeople();

    assertEquals(2, allPeople.size());
    assertEquals(PERSON1_FIST_NAME, allPeople.get(0).getFirstName());
    assertEquals(PERSON2_LAST_NAME, allPeople.get(1).getLastName());
  }

  @Test
  public void createPerson() {
    when(personRepository.save(any(Person.class))).thenReturn(person1);
    when(person1.getId()).thenReturn(123L);

    ResponseEntity<?> response = personController.createPerson(PERSON1_TO, UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("/api/v1/people/123", response.getHeaders().getLocation().getPath());
  }

  @Test
  public void personDetails() {
    when(personRepository.getById(123L)).thenReturn(person1);
    when(person1.getVersion()).thenReturn(456L);

    ResponseEntity<?> response = personController.personDetails(123L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("\"456\"", response.getHeaders().getETag());
    assertEquals(PERSON1_FIST_NAME, ((PersonTO) response.getBody()).getFirstName());
  }
}
