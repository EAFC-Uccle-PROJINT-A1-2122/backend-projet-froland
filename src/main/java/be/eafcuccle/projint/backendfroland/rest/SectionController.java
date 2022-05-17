package be.eafcuccle.projint.backendfroland.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import be.eafcuccle.projint.backendfroland.persistence.Section;
import be.eafcuccle.projint.backendfroland.persistence.SectionRepository;

@RestController
public class SectionController {
  @Autowired
  private SectionRepository sectionRepository;

  @GetMapping("/api/v1/sections")
  public List<Section> listSections() {
    return sectionRepository.findAll();
  }

  @PostMapping("/api/v1/sections")
  public void createSection(@RequestBody Section section) {
    sectionRepository.save(section);
  }
}
