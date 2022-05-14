package be.eafcuccle.projint.backendfroland.persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Session {
  @Id
  @GeneratedValue
  private Long id;

  private LocalDate date;

  private LocalTime beginningTime;

  private LocalTime endTime;

  private int periods;

  public Session(LocalDate date, LocalTime beginningTime, LocalTime endTime, int periods) {
    this.date = date;
    this.beginningTime = beginningTime;
    this.endTime = endTime;
    this.periods = periods;
  }

  protected Session() {
  }

  public Long getId() {
      return id;
  }

  public LocalDate getDate() {
      return date;
  }

  public LocalTime getBeginningTime() {
      return beginningTime;
  }

  public LocalTime getEndTime() {
      return endTime;
  }

  public int getPeriods() {
      return periods;
  }

  @Override
  public boolean equals(Object obj) {
      if (obj == null || obj.getClass() != this.getClass()) return false;
      if (obj == this) return true;

      Session other = (Session) obj;
      return Objects.equals(this.id, other.id);
  }

  @Override
  public int hashCode() {
      return Objects.hash(id);
  }
}
