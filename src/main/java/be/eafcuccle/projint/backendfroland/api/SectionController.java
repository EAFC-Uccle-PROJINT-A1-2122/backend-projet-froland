package be.eafcuccle.projint.backendfroland.api;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@RestController
@RequestMapping("/api/v1/sections")
@CrossOrigin
public class SectionController {
  private final SectionRepository sectionRepository;

  public SectionController(SectionRepository sectionRepository) {
    this.sectionRepository = sectionRepository;
  }

  @GetMapping("")
  public ResponseEntity<List<SectionTO>> allSections() {
    List<Section> sections = sectionRepository.findAll();
    List<SectionTO> sectionsTO = sections.stream().map(s -> new SectionTO(s.getId(), s.getName()))
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(sectionsTO);
  }

  @GetMapping("/raw")
  public ResponseEntity<List<Section>> rawSections() {
    List<Section> sections = sectionRepository.findAll();
    return ResponseEntity.ok(sections);
  }

  @PostMapping("")
  public ResponseEntity<?> createSection(@Valid @RequestBody SectionTO sectionTO, UriComponentsBuilder uriBuilder) {
    Section section = new Section(sectionTO.getName());
    try {
      Long sectionId = sectionRepository.save(section).getId();
      return ResponseEntity.created(uriBuilder.path("/api/v1/sections/{id}").build(sectionId)).build();
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
