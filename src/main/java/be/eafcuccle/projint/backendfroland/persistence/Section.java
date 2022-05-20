package be.eafcuccle.projint.backendfroland.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Section {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(
    name = "section_course",
    joinColumns = @JoinColumn(name = "section_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
  )
  private Collection<Course> courses = new ArrayList<>();

  public Section(String name) {
    this.name = name;
  }

  protected Section() {
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

  public Collection<Course> getCourses() {
    return courses;
  }

  public void setCourses(Collection<Course> courses) {
      this.courses = courses;
  }

  public void addCourse(Course course) {
    courses.add(course);
  }

  @Override
  public boolean equals(Object obj) {
      if (obj == null || obj.getClass() != this.getClass()) return false;
      if (obj == this) return true;

      Section other = (Section) obj;
      return Objects.equals(this.name, other.name);
  }

  @Override
  public int hashCode() {
      return Objects.hash(name);
  }

  @Override
  public String toString() {
    return new ToStringCreator(this).append(name).toString();
  }
}
