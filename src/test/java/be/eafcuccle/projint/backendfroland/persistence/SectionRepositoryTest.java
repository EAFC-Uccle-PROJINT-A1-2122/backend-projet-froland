package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SectionRepositoryTest {
  @Autowired
  private SectionRepository sectionRepository;
  @Autowired
  private CourseRepository courseRepository;

  @Test
  public void saveAndFindById() {
    Section newSection = new Section("Informatique de gestion");
    Long id = sectionRepository.saveAndFlush(newSection).getId();

    Section foundSection = sectionRepository.getById(id);

    assertEquals("Informatique de gestion", foundSection.getName());
  }

  @Test
  public void findByCourseId() {
    Course course = new Course("Bases des réseaux");
    Long courseId = courseRepository.save(course).getId();
    Section section1 = new Section("Informatique de gestion");
    section1.addCourse(course);
    sectionRepository.save(section1);
    Section section2 = new Section("Informatique et systèmes");
    section2.addCourse(course);
    sectionRepository.save(section2);

    Collection<Section> sections = sectionRepository.findByCoursesId(courseId);

    assertTrue(sections.contains(section1));
    assertTrue(sections.contains(section2));
  }
}
