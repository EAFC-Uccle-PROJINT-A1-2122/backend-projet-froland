package be.eafcuccle.projint.backendfroland.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {
  private static final String COURSE1_NAME = "Teacher";
  private static final String COURSE2_NAME = "Student";
  private static final CourseTO COURSE1_TO = new CourseTO(1L, COURSE1_NAME);

  @Mock
  private CourseRepository courseRepository;
  @InjectMocks
  private CourseController courseController;
  @Spy
  private Course course1 = new Course(COURSE1_NAME);
  private Course course2 = new Course(COURSE2_NAME);


  @Test
  public void allCourses() {
    when(courseRepository.findAll()).thenReturn(List.of(course1, course2));

    List<CourseTO> allCourses = courseController.allCourses();

    assertEquals(2, allCourses.size());
    assertEquals(COURSE1_NAME, allCourses.get(0).getName());
    assertEquals(COURSE2_NAME, allCourses.get(1).getName());
  }

  @Test
  public void createCourse() {
    when(courseRepository.save(any(Course.class))).thenReturn(course1);
    when(course1.getId()).thenReturn(123L);

    ResponseEntity<?> response = courseController.createCourse(COURSE1_TO, UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("/api/v1/courses/123", response.getHeaders().getLocation().getPath());
  }

  @Test
  public void courseDetails() {
    when(courseRepository.getById(123L)).thenReturn(course1);
    when(course1.getVersion()).thenReturn(456L);

    ResponseEntity<?> response = courseController.courseDetails(123L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("\"456\"", response.getHeaders().getETag());
    assertEquals(COURSE1_NAME, ((CourseTO) response.getBody()).getName());
  }
}
