package be.eafcuccle.projint.backendfroland.persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.core.style.ToStringCreator;

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

  void setId(Long id) {
    this.id = id;
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

  public boolean hasHappenedBy(LocalDate byDate) {
    return byDate.isEqual(date) || byDate.isAfter(date);
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

  @Override
  public String toString() {
      return new ToStringCreator(this).append("id", id).toString();
  }
}
