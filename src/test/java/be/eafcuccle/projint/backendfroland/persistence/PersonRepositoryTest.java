package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class PersonRepositoryTest {
  @Autowired
  private TestEntityManager em;

  @Autowired
  private PersonRepository sessionRepository;

  @Test
  public void saveAndFindById() {
    Person newPerson = new Person("Hatim", "El Amri");
    Long id = sessionRepository.save(newPerson).getId();
    em.flush();

    Optional<Person> foundPerson = sessionRepository.findById(id);
    assertTrue(foundPerson.isPresent());
  }
}
