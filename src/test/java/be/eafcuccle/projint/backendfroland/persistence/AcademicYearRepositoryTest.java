package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AcademicYearRepositoryTest {
  @Autowired
  private AcademicYearRepository academicYearRepository;

  @Test
  public void saveAndFindById() {
    AcademicYear newAcademicYear = new AcademicYear(LocalDate.of(2021, 9, 1), LocalDate.of(2022, 8, 28));
    Long id = academicYearRepository.saveAndFlush(newAcademicYear).getId();

    AcademicYear foundAcademicYear = academicYearRepository.getById(id);
    assertEquals(2021, foundAcademicYear.getBeginning().getYear());
  }
}
