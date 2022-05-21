package be.eafcuccle.projint.backendfroland.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseClassTO {
  private Long id;
  @NotBlank
  private String identifier;
  @NotNull
  private Long academicYearId;
  @NotNull
  private Long courseId;

  public CourseClassTO(Long id, String identifier, Long courseId, Long academicYearId) {
    this.id = id;
    this.identifier = identifier;
    this.academicYearId = academicYearId;
    this.courseId = courseId;
  }

  public Long getId() {
    return id;
  }

  public String getIdentifier() {
    return identifier;
  }

  public Long getAcademicYearId() {
    return academicYearId;
  }

  public Long getCourseId() {
    return courseId;
  }
}
