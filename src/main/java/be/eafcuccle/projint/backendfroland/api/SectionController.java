package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
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
    List<Section> sections = sectionRepository.findAll(Sort.by("name"));
    List<SectionTO> sectionTOs =
        sections.stream().map(SectionTO::of).collect(Collectors.toList());
    Object[] versions = sections.stream().map((section) -> section.getVersion()).toArray();
    int etag = Objects.hash(versions);
    return ResponseEntity.ok()
      .cacheControl(CacheControl.noCache())
      .eTag(Integer.toString(etag))
      .body(sectionTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SectionTO> sectionDetails(@PathVariable Long id) {
    Section section = sectionRepository.getById(id);
    SectionTO sectionTO = SectionTO.of(section);
    return ResponseEntity.ok()
      .cacheControl(CacheControl.noCache())
      .eTag(Long.toString(section.getVersion()))
      .body(sectionTO);
  }

  @PostMapping("")
  public ResponseEntity<?> createSection(@Valid @RequestBody SectionTO sectionTO,
      UriComponentsBuilder uriBuilder) {
    Section section = sectionTO.convertToEntity();
    try {
      Long sectionId = sectionRepository.save(section).getId();
      URI newSectionUri = uriBuilder.path("/api/v1/sections/{id}").build(sectionId);
      return ResponseEntity.created(newSectionUri).build();
    } catch (DataIntegrityViolationException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
