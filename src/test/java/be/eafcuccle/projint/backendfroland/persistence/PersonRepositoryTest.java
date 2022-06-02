package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PersonRepositoryTest {
  @Autowired
  private PersonRepository sessionRepository;

  @Test
  public void saveAndFindById() {
    Person newPerson = new Person("Hatim", "El Amri");
    Long id = sessionRepository.saveAndFlush(newPerson).getId();

    Person foundPerson = sessionRepository.getById(id);

    assertEquals("Hatim", foundPerson.getFirstName());
  }
}
