package be.eafcuccle.projint.backendfroland.persistence;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AcademicYear {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private LocalDate beginning;

  private LocalDate end;

  public AcademicYear(LocalDate beginning, LocalDate end) {
    this.beginning = beginning;
    this.end = end;
  }

  public Long getId() {
    return id;
  }

  protected AcademicYear() {
  }

  public LocalDate getBeginning() {
    return beginning;
  }

  public LocalDate getEnd() {
    return end;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    if (obj == this)
      return true;

    AcademicYear other = (AcademicYear) obj;
    return Objects.equals(this.beginning, other.beginning) && Objects.equals(this.end, other.end);
  }

  @Override
  public int hashCode() {
      return Objects.hash(beginning, end);
  }

  @Override
  public String toString() {
    return String.format("%d-%d", beginning.getYear(), end.getYear());
  }
}
