package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SessionRepositoryTest {
  @Autowired
  private SessionRepository sessionRepository;

  @Test
  public void saveAndFindById() {
    Session newSession = new Session(LocalDate.of(2022,5,13), LocalTime.of(14,0), LocalTime.of(15,40), 2);
    Long id = sessionRepository.saveAndFlush(newSession).getId();

    Session foundSession = sessionRepository.getById(id);

    assertEquals(2, foundSession.getPeriods());
  }
}
