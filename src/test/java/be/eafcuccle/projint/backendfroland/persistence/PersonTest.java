package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class PersonTest {
  @Test
  public void isAllowedWithoutRole() {
    Person person = new Person("Harry", "Potter");

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

  @Test
  public void isAllowedWithEmptyRole() {
    Person person = new Person("Harry", "Potter");
    Role role = new Role("EMPTY");
    person.grant(role);

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

  @Test
  public void isAllowedWithAllowedRole() {
    Person person = new Person("Harry", "Potter");
    Role role = new Role("ALLOWED");
    role.grant(Permission.READ_PERSON_DETAILS);
    person.grant(role);

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertTrue(result);
  }

  @Test
  public void isAllowedWithRevokedRole() {
    Person person = new Person("Harry", "Potter");
    Role role = new Role("ALLOWED");
    role.grant(Permission.READ_PERSON_DETAILS);
    person.grant(role);
    person.revoke(role);

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

  @Test
  public void computeAttendanceWithNoSession() {
    Person person = new Person("Harry", "Potter");
    CourseClass courseClass = mock(CourseClass.class);

    float result = person.computeAttendance(courseClass, LocalDate.of(2021, 5, 14));

    assertEquals(0, result);
  }

  @Test
  public void computeAttendanceWithAttendances() {
    Person person = new Person("Harry", "Potter");
    Course course = new Course("Défense contre les forces du mal");
    AcademicYear academicYear = new AcademicYear(LocalDate.of(2021, 9, 1), LocalDate.of(2022, 8, 28));
    CourseClass courseClass = new CourseClass("DEFCONMAL", course, academicYear);
    Session session1 = new Session(LocalDate.of(2021, 9, 1), LocalTime.of(9, 0), LocalTime.of(12, 40), 4);
    session1.setId(1L);
    Session session2 = new Session(LocalDate.of(2021, 9, 8), LocalTime.of(9, 0), LocalTime.of(12, 40), 4);
    session2.setId(2L);
    Session session3 = new Session(LocalDate.of(2021, 9, 15), LocalTime.of(9, 0), LocalTime.of(12, 40), 4);
    session3.setId(3L);
    courseClass.addSession(session1);
    courseClass.addSession(session2);
    courseClass.addSession(session3);
    Attendance attendance1 = new Attendance(session1, AttendanceStatus.PRESENT);
    attendance1.setId(11L);
    Attendance attendance2 = new Attendance(session2, AttendanceStatus.ABSENT_WITHOUT_EXCUSE);
    attendance2.setId(12L);
    Attendance attendance3 = new Attendance(session3, AttendanceStatus.PRESENT);
    attendance3.setId(13L);
    person.addAttendance(attendance1);
    person.addAttendance(attendance2);
    person.addAttendance(attendance3);

    float result = person.computeAttendance(courseClass, LocalDate.of(2021, 9, 10));

    assertEquals(0.5F, result);
  }
}
