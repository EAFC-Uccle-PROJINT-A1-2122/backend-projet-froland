package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;

public class AttendanceTest {
  @Test
  public void computeAttendedPeriodsWithAbsenceWithoutExcuse() {
    Session session = mock(Session.class);
    when(session.getPeriods()).thenReturn(4);
    Attendance attendance = new Attendance(session, AttendanceStatus.ABSENT_WITHOUT_EXCUSE);

    int result = attendance.computeAttendedPeriods();

    assertEquals(0, result);
  }

  @Test
  public void computeAttendedPeriodsWithAbsenceWithExcuse() {
    Session session = mock(Session.class);
    when(session.getPeriods()).thenReturn(4);
    Attendance attendance = new Attendance(session, AttendanceStatus.ABSENT_WITH_EXCUSE);

    int result = attendance.computeAttendedPeriods();

    assertEquals(4, result);
  }

  @Test
  public void computeAttendedPeriodsWithAbsenceWithMedicalCertificate() {
    Session session = mock(Session.class);
    when(session.getPeriods()).thenReturn(4);
    Attendance attendance = new Attendance(session, AttendanceStatus.ABSENT_WITH_MEDICAL_CERTIFICATE);

    int result = attendance.computeAttendedPeriods();

    assertEquals(4, result);
  }

  @Test
  public void computeAttendedPeriodsWithAbsenceWithPresenceAndNoPeriod() {
    Session session = mock(Session.class);
    when(session.getPeriods()).thenReturn(4);
    Attendance attendance = new Attendance(session, AttendanceStatus.PRESENT);

    int result = attendance.computeAttendedPeriods();

    assertEquals(4, result);
  }

  @Test
  public void computeAttendedPeriodsWithAbsenceWithPresencePeriods() {
    Session session = mock(Session.class);
    when(session.getPeriods()).thenReturn(4);
    Attendance attendance = new Attendance(session, AttendanceStatus.PRESENT);
    attendance.setPresencePeriods(3);

    int result = attendance.computeAttendedPeriods();

    assertEquals(3, result);
  }
}
