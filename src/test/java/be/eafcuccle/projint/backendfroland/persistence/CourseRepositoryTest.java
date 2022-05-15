package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CourseRepositoryTest {
  @Autowired
  private SectionRepository sectionRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Test
  public void saveAndFindById() {
    Section section = new Section("Informatique de gestion");
    sectionRepository.save(section);
    Course newCourse = new Course("Informatique de gestion");
    section.addCourse(newCourse);
    Long id = courseRepository.saveAndFlush(newCourse).getId();

    Course foundCourse = courseRepository.getById(id);

    assertEquals("Informatique de gestion", foundCourse.getName());
  }
}
