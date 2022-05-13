package be.eafcuccle.projint.backendfroland.persistence;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;
  @ManyToMany(mappedBy = "courses")
  private Collection<Section> sections;

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

  public Collection<Section> getSections() {
      return sections;
  }

  public void addSection(Section section) {
    sections.add(section);
  }

}
