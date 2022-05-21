package be.eafcuccle.projint.backendfroland.api;

public class RoleTO {
  private Long id;
  private String name;

  public RoleTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
      return id;
  }

  public String getName() {
      return name;
  }
}
