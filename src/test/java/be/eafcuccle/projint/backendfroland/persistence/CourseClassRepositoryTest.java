package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CourseClassRepositoryTest {
  @Autowired
  private CourseClassRepository courseClassRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private AcademicYearRepository academicYearRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  public void saveAndFindById() {
    Course course = new Course("Projet d'intégration de développement");
    courseRepository.save(course);
    AcademicYear academicYear = new AcademicYear(LocalDate.of(2021, 9, 1), LocalDate.of(2022, 8, 28));
    academicYearRepository.save(academicYear);
    Person teacher = new Person("Francois", "Derasse");
    personRepository.save(teacher);
    Person student = new Person("Hatim", "El Amri");
    personRepository.save(student);
    CourseClass newCourseClass = new CourseClass("PROJINT-A1-2122", course, academicYear);
    newCourseClass.addTeacher(teacher);
    newCourseClass.addStudent(student);
    Long id = courseClassRepository.save(newCourseClass).getId();
    em.flush();

    Optional<CourseClass> foundCourseClass = courseClassRepository.findById(id);

    assertTrue(foundCourseClass.isPresent());
    assertEquals("PROJINT-A1-2122", foundCourseClass.get().getIdentifier());
  }
}
