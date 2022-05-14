package be.eafcuccle.projint.backendfroland.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RoleTest {
  @Test
  public void isAllowedWithoutPermission() {
    Role role = new Role("TEST");

    boolean result = role.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

  @Test
  public void isAllowedWithPermission() {
    Role role = new Role("TEST");
    role.grant(Permission.READ_PERSON_DETAILS);

    boolean result = role.isAllowed(Permission.READ_PERSON_DETAILS);

    assertTrue(result);
  }

  @Test
  public void isAllowedWithRevokedPermission() {
    Role role = new Role("TEST");
    role.grant(Permission.READ_PERSON_DETAILS);
    role.revoke(Permission.READ_PERSON_DETAILS);

    boolean result = role.isAllowed(Permission.READ_PERSON_DETAILS);

    assertFalse(result);
  }

}
