package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@RestController
@RequestMapping("/api/v1/sections")
@CrossOrigin
public class SectionController {
  private final SectionRepository sectionRepository;
  private final CourseRepository courseRepository;

  public SectionController(SectionRepository sectionRepository, CourseRepository courseRepository) {
    this.sectionRepository = sectionRepository;
    this.courseRepository = courseRepository;
  }

  @GetMapping("")
  public ResponseEntity<List<SectionTO>> allSections() {
    List<Section> sections = sectionRepository.findAll();
    List<SectionTO> sectionsTO =
        sections.stream().map(SectionController::convertEntity).collect(Collectors.toList());
    return ResponseEntity.ok().body(sectionsTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SectionTO> sectionDetails(@PathVariable Long id) {
    Section section = sectionRepository.getById(id);
    SectionTO sectionTO = convertEntity(section);
    return ResponseEntity.ok().eTag(Long.toString(section.getVersion())).body(sectionTO);
  }

  @PostMapping("")
  public ResponseEntity<?> createSection(@Valid @RequestBody SectionTO sectionTO,
      UriComponentsBuilder uriBuilder) {
    Section section = convertTO(sectionTO);
    try {
      Long sectionId = sectionRepository.save(section).getId();
      URI newSectionUri = uriBuilder.path("/api/v1/sections/{id}").build(sectionId);
      return ResponseEntity.created(newSectionUri).build();
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/{id}/courses")
  public ResponseEntity<?> addCourseToSection(@PathVariable Long sectionId,
      @RequestBody IdentifierTO courseIdentifier) {
    Section section = sectionRepository.getById(sectionId);
    Course course = courseRepository.getById(courseIdentifier.getId());
    section.addCourse(course);
    return ResponseEntity.noContent().build();
  }

  private static Section convertTO(SectionTO sectionTO) {
    return new Section(sectionTO.getName());
  }

  private static SectionTO convertEntity(Section section) {
    return new SectionTO(section.getId(), section.getName());
  }
}
