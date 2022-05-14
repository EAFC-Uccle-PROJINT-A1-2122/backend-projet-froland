package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonTest {
  @Test
  public void isAllowedWithoutRole() {
    Person person = new Person("Harry", "Potter");

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

  @Test
  public void isAllowedWithEmptyRole() {
    Person person = new Person("Harry", "Potter");
    Role role = new Role("EMPTY");
    person.grant(role);

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

  @Test
  public void isAllowedWithAllowedRole() {
    Person person = new Person("Harry", "Potter");
    Role role = new Role("ALLOWED");
    role.grant(Permission.READ_PERSON_DETAILS);
    person.grant(role);

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertTrue(result);
  }

  @Test
  public void isAllowedWithRevokedRole() {
    Person person = new Person("Harry", "Potter");
    Role role = new Role("ALLOWED");
    role.grant(Permission.READ_PERSON_DETAILS);
    person.grant(role);
    person.revoke(role);

    boolean result = person.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }
}
