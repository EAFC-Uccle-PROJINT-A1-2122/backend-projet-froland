package be.eafcuccle.projint.backendfroland.api;

import javax.validation.constraints.NotBlank;
import be.eafcuccle.projint.backendfroland.persistence.Section;

public class SectionTO {
  private Long id;

  @NotBlank
  private String name;

  public SectionTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  Section convertToEntity() {
    return new Section(name);
  }

  static SectionTO of(Section section) {
    return new SectionTO(section.getId(), section.getName());
  }
}
