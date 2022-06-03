package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYear;
import be.eafcuccle.projint.backendfroland.persistence.AcademicYearRepository;
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseClass;
import be.eafcuccle.projint.backendfroland.persistence.CourseClassRepository;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;

@RestController
@RequestMapping("/api/v1/courseClasses")
@CrossOrigin
@PreAuthorize("hasAuthority('SCOPE_admin:courses')")
public class CourseClassController {
  private final CourseClassRepository courseClassRepository;
  private final CourseRepository courseRepository;
  private final AcademicYearRepository academicYearRepository;

  public CourseClassController(CourseClassRepository courseClassRepository,
      CourseRepository courseRepository, AcademicYearRepository academicYearRepository) {
    this.courseClassRepository = courseClassRepository;
    this.courseRepository = courseRepository;
    this.academicYearRepository = academicYearRepository;
  }

  @GetMapping
  public List<CourseClassTO> allCourseClasss() {
    List<CourseClass> courseClasss = courseClassRepository.findAll();
    return courseClasss.stream().map(CourseClassController::convertEntity)
        .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseClassTO> courseClassDetails(@PathVariable Long courseClassId) {
    CourseClass courseClass = courseClassRepository.getById(courseClassId);
    CourseClassTO courseClassTO = convertEntity(courseClass);
    return ResponseEntity.ok().eTag(Long.toString(courseClass.getVersion())).body(courseClassTO);
  }

  @PostMapping
  public ResponseEntity<?> createCourseClass(@Valid @RequestBody CourseClassTO courseClassTO,
      UriComponentsBuilder uriBuilder) {
    CourseClass courseClass = convertTO(courseClassTO);
    Long id = courseClassRepository.save(courseClass).getId();
    URI newCourseClassUri = uriBuilder.path("/api/v1/courseClasss/{id}").build(id);
    return ResponseEntity.created(newCourseClassUri).build();
  }

  private CourseClass convertTO(CourseClassTO courseClassTO) {
    Course course = courseRepository.getById(courseClassTO.getCourseId());
    AcademicYear academicYear = academicYearRepository.getById(courseClassTO.getAcademicYearId());
    return new CourseClass(courseClassTO.getIdentifier(), course, academicYear);
  }

  private static CourseClassTO convertEntity(CourseClass courseClass) {
    return new CourseClassTO(courseClass.getId(), courseClass.getIdentifier(),
        courseClass.getCourse().getId(), courseClass.getAcademicYear().getId());
  }
}
