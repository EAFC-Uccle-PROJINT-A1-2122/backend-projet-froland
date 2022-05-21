package be.eafcuccle.projint.backendfroland.rest;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Course;
import be.eafcuccle.projint.backendfroland.persistence.CourseRepository;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@RestController
public class SectionController {
  @Autowired
  private SectionRepository sectionRepository;

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping("/api/v1/sections")
  public List<SectionTO> listSections() {
    List<Section> sections = sectionRepository.findAll();
    return sections.stream().map(s -> new SectionTO(s.getId(), s.getName())).collect(Collectors.toList());
  }

  @PostMapping("/api/v1/sections")
  public ResponseEntity<?> createSection(@Valid @RequestBody SectionTO sectionTO, UriComponentsBuilder uriBuilder) {
    Section newSection = new Section(sectionTO.getName());
    Long id = sectionRepository.save(newSection).getId();
    URI sectionUri = uriBuilder.path("/api/v1/sections/{id}").build(id);
    return ResponseEntity.created(sectionUri).build();
  }

  @GetMapping("/api/v1/sections/{id}")
  public SectionTO getSection(@PathVariable("id") Long id) {
    Section section = sectionRepository.getById(id);
    return new SectionTO(section.getId(), section.getName());
  }

  @PutMapping("/api/v1/sections/{id}/courses")
  public ResponseEntity<?> modifyCourses(@PathVariable("id") Long id, Collection<Long> courseIds) {
    Section section = sectionRepository.getById(id);
    Collection<Course> courses = courseRepository.findAllById(courseIds);
    section.setCourses(courses);
    return ResponseEntity.noContent().build();
  }
}
