package ao.com.ricardo.vacancy_management.modules.company.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class CreateJobDTO {

	@Schema(example = "Develop and maintain REST APIs using Spring Boot and PostgreSQL.", requiredMode = RequiredMode.REQUIRED)
	private String description;

	@Schema(example = "Health insurance, life insurance, meal allowance, and continuous training.", requiredMode = RequiredMode.REQUIRED)
	private String benefits;

	@Schema(example = "Senior", allowableValues = { "Junior", "Mid-level","Senior" }, requiredMode = RequiredMode.REQUIRED)
	private String level;

}
