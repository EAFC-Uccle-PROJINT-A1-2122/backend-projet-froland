package be.eafcuccle.projint.backendfroland.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Role;
import be.eafcuccle.projint.backendfroland.persistence.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {
  private static final String ROLE1_NAME = "Teacher";
  private static final String ROLE2_NAME = "Student";
  private static final RoleTO ROLE1_TO = new RoleTO(1L, ROLE1_NAME);

  @Mock
  private RoleRepository roleRepository;
  @InjectMocks
  private RoleController roleController;
  @Spy
  private Role role1 = new Role(ROLE1_NAME);
  private Role role2 = new Role(ROLE2_NAME);


  @Test
  public void allRoles() {
    when(roleRepository.findAll()).thenReturn(List.of(role1, role2));

    List<RoleTO> allRoles = roleController.allRoles();

    assertEquals(2, allRoles.size());
    assertEquals(ROLE1_NAME, allRoles.get(0).getName());
    assertEquals(ROLE2_NAME, allRoles.get(1).getName());
  }

  @Test
  public void createRole() {
    when(roleRepository.save(any(Role.class))).thenReturn(role1);
    when(role1.getId()).thenReturn(123L);

    ResponseEntity<?> response = roleController.createRole(ROLE1_TO, UriComponentsBuilder.newInstance());

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("/api/v1/roles/123", response.getHeaders().getLocation().getPath());
  }

  @Test
  public void roleDetails() {
    when(roleRepository.getById(123L)).thenReturn(role1);
    when(role1.getVersion()).thenReturn(456L);

    ResponseEntity<?> response = roleController.roleDetails(123L);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("\"456\"", response.getHeaders().getETag());
    assertEquals(ROLE1_NAME, ((RoleTO) response.getBody()).getName());
  }
}
