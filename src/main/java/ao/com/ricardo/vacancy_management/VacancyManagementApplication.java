package ao.com.ricardo.vacancy_management;

import org.hibernate.annotations.SecondaryRow;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Vacancy Management API",
		version = "1.0",
		description = "API for managing job vacancies"
	)
)
@SecurityScheme(name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class VacancyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyManagementApplication.class, args);
	}
}
