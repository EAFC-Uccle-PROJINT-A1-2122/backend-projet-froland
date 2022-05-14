package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class AttendanceRepositoryTest {
  @Autowired
  private AttendanceRepository attendanceRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  public void saveAndFindById() {
    Person student = new Person("Hatim", "El Amri");
    personRepository.save(student);
    Session session = new Session(LocalDate.of(2022,5,13), LocalTime.of(14,0), LocalTime.of(15,40), 2);
    Long sessionId = sessionRepository.save(session).getId();
    Attendance newAttendance = new Attendance(session, AttendanceStatus.PRESENT);
    student.addAttendance(newAttendance);
    Long id = attendanceRepository.save(newAttendance).getId();
    em.flush();

    Optional<Attendance> foundAttendance = attendanceRepository.findById(id);

    assertTrue(foundAttendance.isPresent());
    assertEquals(sessionId, foundAttendance.get().getSession().getId());
    assertEquals(AttendanceStatus.PRESENT, foundAttendance.get().getStatus());
  }
}
