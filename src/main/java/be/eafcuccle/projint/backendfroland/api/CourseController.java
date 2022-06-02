package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@RestController
@RequestMapping("/api/v1/courses")
@CrossOrigin
public class CourseController {
  private final CourseRepository courseRepository;
  private final SectionRepository sectionRepository;

  public CourseController(CourseRepository courseRepository, SectionRepository sectionRepository) {
    this.courseRepository = courseRepository;
    this.sectionRepository = sectionRepository;
  }

  @GetMapping
  public List<CourseTO> allCourses() {
    List<Course> courses = courseRepository.findAll(Sort.by("name"));
    return courses.stream().map(CourseController::convertEntity).collect(Collectors.toList());
  }

  @GetMapping("/{courseId}")
  public ResponseEntity<CourseTO> courseDetails(@PathVariable(name = "courseId") Long courseId) {
    Course course = courseRepository.getById(courseId);
    CourseTO courseTO = convertEntity(course);
    return ResponseEntity.ok().eTag(Long.toString(course.getVersion())).body(courseTO);
  }

  @PostMapping
  public ResponseEntity<?> createCourse(@Valid @RequestBody CourseTO courseTO, UriComponentsBuilder uriBuilder) {
    Course course = convertTO(courseTO);
    Long id = courseRepository.save(course).getId();
    URI newCourseUri = uriBuilder.path("/api/v1/courses/{id}").build(id);
    return ResponseEntity.created(newCourseUri).build();
  }

  @GetMapping("/{courseId}/sections")
  public Collection<SectionTO> courseSections(@PathVariable(name = "courseId") Long courseId) {
    Collection<Section> sections = sectionRepository.findByCoursesId(courseId);
    return sections.stream().map(SectionTO::of).collect(Collectors.toList());
  }

  @PostMapping("/{courseId}/sections")
  public ResponseEntity<?> addCourseSection(@PathVariable(name = "courseId") Long courseId, @RequestBody IdentifierTO sectionIdentifier, UriComponentsBuilder uriBuilder) {
    Course course = courseRepository.getById(courseId);
    Section section = sectionRepository.getById(sectionIdentifier.getId());
    section.addCourse(course);
    sectionRepository.save(section);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{courseId}/sections")
  public ResponseEntity<?> removeCourseSection(@PathVariable(name = "courseId") Long courseId, @RequestBody IdentifierTO sectionIdentifier, UriComponentsBuilder uriBuilder) {
    Course course = courseRepository.getById(courseId);
    Section section = sectionRepository.getById(sectionIdentifier.getId());
    section.removeCourse(course);
    sectionRepository.save(section);
    return ResponseEntity.noContent().build();
  }

  private static Course convertTO(CourseTO courseTO) {
    return new Course(courseTO.getName());
  }

  private static CourseTO convertEntity(Course course) {
    return new CourseTO(course.getId(), course.getName());
  }
}
