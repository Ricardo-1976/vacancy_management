package ao.com.ricardo.vacancy_management.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import ao.com.ricardo.vacancy_management.modules.company.dtos.AuthCompanyDTO;
import ao.com.ricardo.vacancy_management.modules.company.dtos.AuthCompanyResponseDTO;
import ao.com.ricardo.vacancy_management.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

	@Value("${security.token.secret}")
	private String secretKey;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO ) throws AuthenticationException {
		var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
				() -> {
					throw new UsernameNotFoundException("Username/password incorrect");
				});
		var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

		if(!passwordMatches) {
			throw new AuthenticationException();
		}

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		var expiresIn = Instant.now().plus(Duration.ofDays(10));
		var token = JWT.create().withIssuer("javagas")
				.withSubject(company.getId().toString())
				.withClaim("roles", Arrays.asList("COMPANY"))
				.withExpiresAt(expiresIn)
				.sign(algorithm);

			var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
			.access_token(token)
			.expires_in(expiresIn.toEpochMilli())
			.build();

		return authCompanyResponseDTO;
	}
} 
