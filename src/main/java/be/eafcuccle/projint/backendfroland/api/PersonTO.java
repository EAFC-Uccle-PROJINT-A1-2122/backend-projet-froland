package be.eafcuccle.projint.backendfroland.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonTO {
  private Long id;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @Email
  private String emailAddres;
  private String phoneNumber;

  public PersonTO(Long id, String firstName, String lastName, String emailAddres,
      String phoneNumber) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddres = emailAddres;
    this.phoneNumber = phoneNumber;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddres() {
    return emailAddres;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }
}
