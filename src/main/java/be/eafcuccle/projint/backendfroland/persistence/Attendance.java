package be.eafcuccle.projint.backendfroland.persistence;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Attendance {
  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JoinColumn(name = "session_id")
  private Session session;

  @Enumerated(EnumType.STRING)
  private AttendanceStatus status;

  private Integer presencePeriods;

  public Attendance(Session session, AttendanceStatus status) {
    this.session = session;
    this.status = status;
  }

  protected Attendance() {
  }

  public Long getId() {
    return id;
  }

  void setId(Long id) {
    this.id = id;
  }

  public Session getSession() {
    return session;
  }

  public AttendanceStatus getStatus() {
    return status;
  }

  public void setStatus(AttendanceStatus status) {
    this.status = status;
  }

  public Integer getPresencePeriods() {
    return presencePeriods;
  }

  public void setPresencePeriods(Integer presencePeriods) {
    this.presencePeriods = presencePeriods;
  }

  public int computeAttendedPeriods() {
    switch (status) {
      case ABSENT_WITHOUT_EXCUSE:
        return 0;
      case ABSENT_WITH_EXCUSE:
        return session.getPeriods();
      case ABSENT_WITH_MEDICAL_CERTIFICATE:
        return session.getPeriods();
      case PRESENT:
        if (presencePeriods != null)
          return presencePeriods;
        else
          return session.getPeriods();
      default:
        throw new IllegalStateException("Unknown status");
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    if (obj == this)
      return true;

    Attendance other = (Attendance) obj;
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
