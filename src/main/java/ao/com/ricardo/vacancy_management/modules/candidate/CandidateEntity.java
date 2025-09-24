package ao.com.ricardo.vacancy_management.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

  private UUID id;

 @Length(min = 3, max = 20, message = " The [Username] field must contain between 3 and 20 characters ")
  private String username;
  private String name;

  @Email(message = " The [Email] field must contain a valid email address ")
  private String email;

  @Length(min = 6, message = " The [Password] field must contain at least 6 characters ")
  private String password;
  private String description;
  private String curriculum;
  
}
