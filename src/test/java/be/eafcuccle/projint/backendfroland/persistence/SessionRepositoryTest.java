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
public class SessionRepositoryTest {
  @Autowired
  private TestEntityManager em;

  @Autowired
  private SessionRepository sessionRepository;

  @Test
  public void saveAndFindById() {
    Session newSession = new Session(LocalDate.of(2022,5,13), LocalTime.of(14,0), LocalTime.of(15,40), 2);
    Long id = sessionRepository.save(newSession).getId();
    em.flush();

    Optional<Session> foundSession = sessionRepository.findById(id);
    assertTrue(foundSession.isPresent());
  }
}
