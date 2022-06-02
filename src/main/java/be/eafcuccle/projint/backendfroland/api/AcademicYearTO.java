package be.eafcuccle.projint.backendfroland.api;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;

public class AcademicYearTO {
  private Long id;
  @NotNull
  private LocalDate beginning;
  @NotNull
  private LocalDate end;

  public AcademicYearTO(Long id, LocalDate beginning, LocalDate end) {
    this.id = id;
    this.beginning = beginning;
    this.end = end;
  }

  public Long getId() {
      return id;
  }

  public LocalDate getBeginning() {
    return beginning;
  }

  public LocalDate getEnd() {
    return end;
  }
}
