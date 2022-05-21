package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
  private final CourseRepository courseRepository;

  public CourseController(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @GetMapping
  public List<CourseTO> allCourses() {
    List<Course> courses = courseRepository.findAll();
    return courses.stream().map(CourseController::convertEntity).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseTO> courseDetails(@PathVariable Long courseId) {
    Course course = courseRepository.getById(courseId);
    CourseTO courseTO = convertEntity(course);
    return ResponseEntity.ok().eTag(Long.toString(course.getVersion())).body(courseTO);
  }

  @PostMapping
  public ResponseEntity<?> createCourse(@RequestBody CourseTO courseTO, UriComponentsBuilder uriBuilder) {
    Course course = convertTO(courseTO);
    Long id = courseRepository.save(course).getId();
    URI newCourseUri = uriBuilder.path("/api/v1/courses/{id}").build(id);
    return ResponseEntity.created(newCourseUri).build();
  }

  private static Course convertTO(CourseTO courseTO) {
    return new Course(courseTO.getName());
  }

  private static CourseTO convertEntity(Course course) {
    return new CourseTO(course.getId(), course.getName());
  }
}
