package be.eafcuccle.projint.backendfroland.api;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import be.eafcuccle.projint.backendfroland.persistence.Role;
import be.eafcuccle.projint.backendfroland.persistence.RoleRepository;

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin
@PreAuthorize("hasAuthority('SCOPE_admin:people')")
public class RoleController {
  private final RoleRepository roleRepository;

  public RoleController(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @GetMapping
  public List<RoleTO> allRoles() {
    List<Role> roles = roleRepository.findAll();
    return roles.stream().map(RoleController::convertEntity).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoleTO> roleDetails(@PathVariable Long roleId) {
    Role role = roleRepository.getById(roleId);
    RoleTO roleTO = convertEntity(role);
    return ResponseEntity.ok().eTag(Long.toString(role.getVersion())).body(roleTO);
  }

  @PostMapping
  public ResponseEntity<?> createRole(@Valid @RequestBody RoleTO roleTO, UriComponentsBuilder uriBuilder) {
    Role role = convertTO(roleTO);
    Long id = roleRepository.save(role).getId();
    URI newRoleUri = uriBuilder.path("/api/v1/roles/{id}").build(id);
    return ResponseEntity.created(newRoleUri).build();
  }

  private static Role convertTO(RoleTO roleTO) {
    return new Role(roleTO.getName());
  }

  private static RoleTO convertEntity(Role role) {
    return new RoleTO(role.getId(), role.getName());
  }
}
