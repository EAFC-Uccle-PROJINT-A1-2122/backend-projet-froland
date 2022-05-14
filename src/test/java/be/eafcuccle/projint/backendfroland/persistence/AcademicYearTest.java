package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AcademicYearTest {
  private LocalDate beginning = LocalDate.of(2021, 9, 1);
  private LocalDate end = LocalDate.of(2022, 8, 28);

  @Test
  public void containsWithDateWithinInterval() {
    AcademicYear year = new AcademicYear(beginning, end);

    boolean result = year.contains(LocalDate.of(2022,1,1));

    assertTrue(result);
  }

  @Test
  public void containsWithDateOutsideInterval() {
    AcademicYear year = new AcademicYear(beginning, end);

    boolean result = year.contains(LocalDate.of(2021,1,1));

    assertFalse(result);
  }

  @Test
  public void containsWithBeginningDate() {
    AcademicYear year = new AcademicYear(beginning, end);

    boolean result = year.contains(beginning);

    assertTrue(result);
  }

  @Test
  public void containsWithEndDate() {
    AcademicYear year = new AcademicYear(beginning, end);

    boolean result = year.contains(end);

    assertTrue(result);
  }
}
