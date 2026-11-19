package ao.com.ricardo.vacancy_management.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateRequestDTO {
  
  
  private UUID id;
  @Schema (example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
  private String name;
  @Schema (example = "john.doe@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
  private String email;
  @Schema (example = "johndoe", requiredMode = Schema.RequiredMode.REQUIRED)
  private String username;
  @Schema (example = "Software Engineer with 5 years of experience.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  private String description;
}
