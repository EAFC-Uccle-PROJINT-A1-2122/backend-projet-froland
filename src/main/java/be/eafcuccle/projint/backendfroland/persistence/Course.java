package be.eafcuccle.projint.backendfroland.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Course {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

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
}
