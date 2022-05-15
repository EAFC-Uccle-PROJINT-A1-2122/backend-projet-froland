package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RoleRepositoryTest {
  @Autowired
  private RoleRepository repository;

  @Test
  public void saveAndFindById() {
    Role newRole = new Role("Enseignant");
    newRole.grant(Permission.READ_PERSON_DETAILS);
    Long id = repository.saveAndFlush(newRole).getId();

    Role foundRole = repository.getById(id);

    assertEquals("Enseignant", foundRole.getName());
    assertTrue(foundRole.isAllowed(Permission.READ_PERSON_DETAILS));
  }
}
