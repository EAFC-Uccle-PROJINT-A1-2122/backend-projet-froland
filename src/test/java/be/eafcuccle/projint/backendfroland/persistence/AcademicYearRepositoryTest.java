package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class AcademicYearRepositoryTest {
  @Autowired
  private TestEntityManager em;

  @Autowired
  private AcademicYearRepository academicYearRepository;

  @Test
  public void saveAndFindById() {
    AcademicYear newAcademicYear = new AcademicYear(2021);
    Long id = academicYearRepository.save(newAcademicYear).getId();
    em.flush();

    Optional<AcademicYear> foundAcademicYear = academicYearRepository.findById(id);
    assertTrue(foundAcademicYear.isPresent());
    assertEquals(2021, foundAcademicYear.get().getBeginningYear());
  }
}
