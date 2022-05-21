package be.eafcuccle.projint.backendfroland.api;

import javax.validation.constraints.NotBlank;

public class CourseTO {
  private Long id;

  @NotBlank
  private String name;

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
}
