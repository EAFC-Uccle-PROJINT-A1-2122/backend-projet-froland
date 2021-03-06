package be.eafcuccle.projint.backendfroland.persistence;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Version;
import org.springframework.core.style.ToStringCreator;

@Entity
public class Role {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"))
  @Column(name = "permission")
  private Set<Permission> permissions = new HashSet<>();

  @Version
  private long version;

  public Role(String name) {
    this.name = name;
  }

  protected Role() {}

  public Long getId() {
      return id;
  }

  public String getName() {
      return name;
  }

  public void grant(Permission permission) {
    permissions.add(permission);
  }

  public void revoke(Permission permission) {
    permissions.remove(permission);
  }

  public boolean isAllowed(Permission permission) {
    return permissions.contains(permission);
  }

  public long getVersion() {
      return version;
  }

  @Override
  public boolean equals(Object obj) {
      if (obj == null || obj.getClass() != this.getClass()) return false;
      if (obj == this) return true;

      Role other = (Role) obj;
      return Objects.equals(this.name, other.name);
  }

  @Override
  public int hashCode() {
      return Objects.hash(name);
  }

  @Override
  public String toString() {
    return new ToStringCreator(this).append("name", name).toString();
  }
}
