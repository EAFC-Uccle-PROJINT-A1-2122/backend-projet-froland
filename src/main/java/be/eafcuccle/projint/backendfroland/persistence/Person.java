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
import javax.persistence.OneToMany;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Person {
  @Id
  @GeneratedValue
  private Long id;

  private String firstName;

  private String lastName;

  private String emailAddress;

  private String phoneNumber;

  @ManyToMany
  @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  @OneToMany
  @JoinColumn(name = "student_id")
  private Set<Attendance> attendances = new HashSet<>();

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  protected Person() {
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void grant(Role role) {
    roles.add(role);
  }

  public void revoke(Role role) {
    roles.remove(role);
  }

  public Set<Attendance> getAttendances() {
    return attendances;
  }

  public void addAttendance(Attendance attendance) {
    attendances.add(attendance);
  }

  public void removeAttendance(Attendance attendance) {
    attendances.remove(attendance);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    if (obj == this)
      return true;

    Person other = (Person) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public int hashCode() {
      return Objects.hash(id);
  }

  @Override
  public String toString() {
      String name = String.format("%s %s", firstName, lastName);
      return new ToStringCreator(this).append(name).append("id", id).toString();
  }
}
