package be.eafcuccle.projint.backendfroland.persistence;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import org.springframework.core.style.ToStringCreator;

@Entity
public class AcademicYear {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDate beginningDate;

  private LocalDate endDate;

  @Version
  private long version;

  public AcademicYear(LocalDate beginning, LocalDate end) {
    this.beginningDate = beginning;
    this.endDate = end;
  }

  public Long getId() {
    return id;
  }

  protected AcademicYear() {
  }

  public LocalDate getBeginning() {
    return beginningDate;
  }

  public LocalDate getEnd() {
    return endDate;
  }

  public long getVersion() {
      return version;
  }

  public boolean contains(LocalDate date) {
    return date.isEqual(beginningDate) || (date.isAfter(beginningDate) && date.isBefore(endDate)) || date.isEqual(endDate);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    if (obj == this)
      return true;

    AcademicYear other = (AcademicYear) obj;
    return Objects.equals(this.beginningDate, other.beginningDate) && Objects.equals(this.endDate, other.endDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(beginningDate, endDate);
  }

  @Override
  public String toString() {
    String yearString = String.format("%d-%d", beginningDate.getYear(), endDate.getYear());
    return new ToStringCreator(this).append(yearString).toString();
  }
}
