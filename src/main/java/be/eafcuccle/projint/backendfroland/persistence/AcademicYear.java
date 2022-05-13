package be.eafcuccle.projint.backendfroland.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AcademicYear {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private int beginningYear;

  public AcademicYear(int beginningYear) {
    this.beginningYear = beginningYear;
  }

  public Long getId() {
      return id;
  }

  protected AcademicYear() {
  }

  public int getBeginningYear() {
    return beginningYear;
  }

  public int getEndYear() {
    return beginningYear + 1;
  }
}
