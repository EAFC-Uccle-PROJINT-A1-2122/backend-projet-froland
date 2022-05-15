package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@RestController
@RequestMapping("/api/v1/sections")
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
    return ResponseEntity.ok(sectionsTO);
  }

  @PostMapping("")
  public ResponseEntity<?> createSection(@Valid @RequestBody SectionTO sectionTO) {
    Section section = new Section(sectionTO.getName());
    try {
      Long sectionId = sectionRepository.save(section).getId();
      return ResponseEntity.created(URI.create("/api/v1/sections/" + sectionId)).build();
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
