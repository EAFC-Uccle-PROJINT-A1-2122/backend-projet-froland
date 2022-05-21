package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class CourseClassTest {
  private static final String PROJINT_A1_2122 = "PROJINT-A1-2122";
  private static LocalDate DATE1 = LocalDate.of(2003, 6, 23);
  private Course course = mock(Course.class);
  private AcademicYear academicYear = mock(AcademicYear.class);

  @Test
  public void computePeriodTotalWithNoSession() {
    CourseClass courseClass = new CourseClass(PROJINT_A1_2122, course, academicYear);

    int result = courseClass.computePeriodTotal();

    assertEquals(0, result);
  }

  @Test
  public void computePeriodTotalWithTwoSessions() {
    CourseClass courseClass = new CourseClass(PROJINT_A1_2122, course, academicYear);
    Session session1 = mock(Session.class);
    when(session1.getPeriods()).thenReturn(4);
    Session session2 = mock(Session.class);
    when(session2.getPeriods()).thenReturn(2);
    courseClass.addSession(session1);
    courseClass.addSession(session2);

    int result = courseClass.computePeriodTotal();

    assertEquals(6, result);
  }

  @Test
  public void computePeriodTotalByDateWithNoSession() {
    CourseClass courseClass = new CourseClass(PROJINT_A1_2122, course, academicYear);

    int result = courseClass.computePeriodTotalByDate(DATE1);

    assertEquals(0, result);
  }

  @Test
  public void computePeriodTotalByDateWithTwoSessions() {
    CourseClass courseClass = new CourseClass(PROJINT_A1_2122, course, academicYear);
    Session session1 = mock(Session.class);
    when(session1.getPeriods()).thenReturn(4);
    when(session1.hasHappenedBy(DATE1)).thenReturn(true);
    Session session2 = mock(Session.class);
    when(session2.getPeriods()).thenReturn(2);
    when(session2.hasHappenedBy(DATE1)).thenReturn(false);
    courseClass.addSession(session1);
    courseClass.addSession(session2);

    int result = courseClass.computePeriodTotalByDate(DATE1);

    assertEquals(4, result);
  }
}
