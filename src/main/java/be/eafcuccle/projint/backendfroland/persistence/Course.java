package be.eafcuccle.projint.backendfroland.persistence;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import org.springframework.core.style.ToStringCreator;

@Entity
public class Course {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @Version
  private long version;

  public Course(String name) {
    this.name = name;
  }

  protected Course() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getVersion() {
      return version;
  }

  @Override
  public boolean equals(Object obj) {
      if (obj == null || obj.getClass() != this.getClass()) return false;
      if (obj == this) return true;

      Course other = (Course) obj;
      return Objects.equals(this.id, other.id) && this.version == other.version;
  }

  @Override
  public int hashCode() {
      return Objects.hash(id, version);
  }

  @Override
  public String toString() {
    return new ToStringCreator(this).append("name", name).append("v", version).toString();
  }
}
