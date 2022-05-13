package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CourseRepositoryTest {
  @Autowired
  private SectionRepository sectionRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  public void saveAndFindById() {
    Section section = new Section("Informatique de gestion");
    sectionRepository.save(section);
    Course newCourse = new Course("Informatique de gestion");
    section.addCourse(newCourse);
    Long id = courseRepository.save(newCourse).getId();
    em.flush();

    Optional<Course> foundCourse = courseRepository.findById(id);

    assertTrue(foundCourse.isPresent());
    assertEquals("Informatique de gestion", foundCourse.get().getName());
  }
}
