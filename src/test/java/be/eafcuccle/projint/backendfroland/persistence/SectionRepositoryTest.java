package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SectionRepositoryTest {
  @Autowired
  private SectionRepository sectionRepository;

  @Test
  public void saveAndFindById() {
    Section newSection = new Section("Informatique de gestion");
    Long id = sectionRepository.saveAndFlush(newSection).getId();

    Section foundSection = sectionRepository.getById(id);

    assertEquals("Informatique de gestion", foundSection.getName());
  }
}
