package be.eafcuccle.projint.backendfroland.persistence;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.springframework.core.style.ToStringCreator;

@Entity
public class CourseClass {
  @Id
  @GeneratedValue
  private Long id;

  private String identifier;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @ManyToOne
  @JoinColumn(name = "academic_year_id")
  private AcademicYear academicYear;

  @OneToMany
  @JoinColumn(name = "course_class_id")
  private Set<Session> sessions = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "course_class_teacher", joinColumns = @JoinColumn(name = "course_class_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
  private Set<Person> teachers = new HashSet<>();

  @ManyToMany
  @JoinTable(name = "course_class_student", joinColumns = @JoinColumn(name = "course_class_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
  private Set<Person> students = new HashSet<>();

  @Version
  private long version;

  public CourseClass(String identifier, Course course, AcademicYear academicYear) {
    this.identifier = identifier;
    this.course = course;
    this.academicYear = academicYear;
  }

  protected CourseClass() {
  }

  public Long getId() {
    return id;
  }

  public String getIdentifier() {
    return identifier;
  }

  public AcademicYear getAcademicYear() {
    return academicYear;
  }

  public Course getCourse() {
    return course;
  }

  public Set<Person> getTeachers() {
    return teachers;
  }

  public void addTeacher(Person teacher) {
    teachers.add(teacher);
  }

  public void removeTeacher(Person teacher) {
    teachers.remove(teacher);
  }

  public Set<Person> getStudents() {
    return students;
  }

  public void addStudent(Person student) {
    students.add(student);
  }

  public void removeStudent(Person student) {
    students.remove(student);
  }

  public int computePeriodTotal() {
    return sessions.stream().mapToInt(Session::getPeriods).sum();
  }

  Set<Session> getSessionsByDate(LocalDate date) {
    return sessions.stream().filter(s -> s.hasHappenedBy(date)).collect(Collectors.toSet());
  }

  public int computePeriodTotalByDate(LocalDate date) {
    return getSessionsByDate(date).stream().mapToInt(Session::getPeriods).sum();
  }

  public Set<Session> getSessions() {
      return sessions;
  }

  public void addSession(Session session) {
    sessions.add(session);
  }

  public long getVersion() {
      return version;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    if (obj == this)
      return true;

    CourseClass other = (CourseClass) obj;
    return Objects.equals(this.identifier, other.identifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifier);
  }

  @Override
  public String toString() {
    return new ToStringCreator(this).append("id", id).toString();
  }

}
