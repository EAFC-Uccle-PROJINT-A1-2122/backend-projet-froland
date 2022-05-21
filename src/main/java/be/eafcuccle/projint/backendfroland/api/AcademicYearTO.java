package be.eafcuccle.projint.backendfroland.api;

import java.time.LocalDate;

public class AcademicYearTO {
  private Long id;
  private LocalDate beginning;
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
