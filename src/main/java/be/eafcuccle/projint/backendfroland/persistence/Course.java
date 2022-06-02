package be.eafcuccle.projint.backendfroland.persistence;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;
import org.springframework.core.style.ToStringCreator;

@Entity
public class Course {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
    name = "section_course",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "section_id", referencedColumnName = "id")
  )
  private Set<Section> sections = new HashSet<>();

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

  public Set<Section> getSections() {
      return sections;
  }

  public void setSections(Set<Section> sections) {
      this.sections = sections;
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
