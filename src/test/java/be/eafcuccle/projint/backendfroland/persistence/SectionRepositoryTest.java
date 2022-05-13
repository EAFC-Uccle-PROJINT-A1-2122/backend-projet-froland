package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class SectionRepositoryTest {
  @Autowired
  private SectionRepository repository;

  @Autowired
  private TestEntityManager em;

  @Test
  public void saveAndFindById() {
    Section newSection = new Section("Informatique de gestion");
    Long id = repository.save(newSection).getId();
    em.flush();

    Optional<Section> foundSection = repository.findById(id);

    assertTrue(foundSection.isPresent());
    assertEquals("Informatique de gestion", foundSection.get().getName());
  }
}
