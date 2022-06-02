package be.eafcuccle.projint.backendfroland.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYear;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYearRepository;
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseClass;
import be.eafcuccle.projint.backendfroland.persistence.CourseClassRepository;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseClassControllerTest {
  private static final String COURSE1_NAME = "Projet d'intégration de développement";
  private static final String COURSE2_NAME = "Projet de développement web";
  private static final String COURSE_CLASS1_IDENTIFIER = "PROJINT_A1_2122";
  private static final String COURSE_CLASS2_IDENTIFIER = "PROJWEB2_S1_2122";
  private static final CourseClassTO COURSE_CLASS1_TO = new CourseClassTO(1L, COURSE_CLASS1_IDENTIFIER, 234L, 345L);

  @Mock
  private CourseClassRepository courseClassRepository;
  @Mock
  private CourseRepository courseRepository;
  @Mock
  private AcademicYearRepository academicYearRepository;
  @InjectMocks
  private CourseClassController courseClassController;
  private Course course1 = new Course(COURSE1_NAME);
  private Course course2 = new Course(COURSE2_NAME);
  private AcademicYear academicYear = new AcademicYear(LocalDate.of(2020, 9, 1), LocalDate.of(2021, 6, 30));
  @Spy
  private CourseClass courseClass1 = new CourseClass(COURSE_CLASS1_IDENTIFIER, course1, academicYear);
  private CourseClass courseClass2 = new CourseClass(COURSE_CLASS2_IDENTIFIER, course2, academicYear);


  @Test
  public void allCourseClasss() {
    when(courseClassRepository.findAll()).thenReturn(List.of(courseClass1, courseClass2));

    List<CourseClassTO> allCourseClasss = courseClassController.allCourseClasss();

    assertEquals(2, allCourseClasss.size());
    assertEquals(COURSE_CLASS1_IDENTIFIER, allCourseClasss.get(0).getIdentifier());
    assertEquals(COURSE_CLASS2_IDENTIFIER, allCourseClasss.get(1).getIdentifier());
  }

  @Test
  public void createCourseClass() {
    when(courseClassRepository.save(any(CourseClass.class))).thenReturn(courseClass1);
    when(courseClass1.getId()).thenReturn(123L);

    ResponseEntity<?> response = courseClassController.createCourseClass(COURSE_CLASS1_TO, UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("/api/v1/courseClasss/123", response.getHeaders().getLocation().getPath());
  }

  @Test
  public void courseClassDetails() {
    when(courseClassRepository.getById(123L)).thenReturn(courseClass1);
    when(courseClass1.getVersion()).thenReturn(456L);

    ResponseEntity<?> response = courseClassController.courseClassDetails(123L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("\"456\"", response.getHeaders().getETag());
    assertEquals(COURSE_CLASS1_IDENTIFIER, ((CourseClassTO) response.getBody()).getIdentifier());
  }
}
