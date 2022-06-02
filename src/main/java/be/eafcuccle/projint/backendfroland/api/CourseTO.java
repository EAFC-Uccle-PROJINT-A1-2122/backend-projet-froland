package be.eafcuccle.projint.backendfroland.api;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;

public class CourseTO {
  private Long id;

  @NotBlank
  private String name;

  private Set<Long> sectionIds = new HashSet<>();

  public CourseTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Long> getSectionIds() {
    return sectionIds;
  }

  public void setSectionIds(Set<Long> sectionIds) {
    this.sectionIds = sectionIds;
  }
}
