package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
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
    AcademicYear newAcademicYear = new AcademicYear(LocalDate.of(2021, 9, 1), LocalDate.of(2022, 8, 28));
    Long id = academicYearRepository.save(newAcademicYear).getId();
    em.flush();

    Optional<AcademicYear> foundAcademicYear = academicYearRepository.findById(id);
    assertTrue(foundAcademicYear.isPresent());
    assertEquals(2021, foundAcademicYear.get().getBeginning().getYear());
  }
}
