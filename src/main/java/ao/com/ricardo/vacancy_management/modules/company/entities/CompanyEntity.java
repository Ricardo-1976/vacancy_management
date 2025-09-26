package ao.com.ricardo.vacancy_management.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;

	@NotBlank(message = " The [Username] field is required ")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = " The [Username] field must contain only letters, numbers, and underscores ")
	private String username;

	@Email(message = " The [Email] field must contain a valid email address ")
	private String email;

	@Length(min = 6, message = " The [Password] field must contain at least 6 characters ")
	private String password;
	private String description;
	private String website;

	@CreationTimestamp
	private LocalDateTime createdAt;
}
