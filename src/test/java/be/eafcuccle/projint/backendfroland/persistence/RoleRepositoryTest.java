package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class RoleRepositoryTest {
  @Autowired
  private RoleRepository repository;

  @Autowired
  private TestEntityManager em;

  @Test
  public void saveAndFindById() {
    Role newRole = new Role("Enseignant");
    newRole.grant(Permission.READ_PERSON_DETAILS);
    Long id = repository.save(newRole).getId();
    em.flush();

    Optional<Role> foundRole = repository.findById(id);

    assertTrue(foundRole.isPresent());
    assertEquals("Enseignant", foundRole.get().getName());
    assertTrue(foundRole.get().isAllowed(Permission.READ_PERSON_DETAILS));
  }
}
