package ao.com.ricardo.vacancy_management.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.com.ricardo.vacancy_management.modules.candidate.entities.CandidateEntity;
import ao.com.ricardo.vacancy_management.modules.candidate.useCases.CreateCandidateUseCase;
import ao.com.ricardo.vacancy_management.modules.candidate.useCases.ListAllJobsByFilterUseCase;
import ao.com.ricardo.vacancy_management.modules.candidate.useCases.ProfileCandidateUseCase;
import ao.com.ricardo.vacancy_management.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CreateCandidateUseCase createCandidateUseCase;

	@Autowired
	private ProfileCandidateUseCase profileCandidateUseCase;

	@Autowired
	private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

	@PostMapping("/")
	public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
		try {
			var result = this.createCandidateUseCase.execute(candidateEntity);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/")
	@PreAuthorize("hasRole('CANDIDATE')")
	public ResponseEntity<Object> get(HttpServletRequest request) {
		var idCandidate = request.getAttribute("candidate_id");
		try {
			var profile = this.profileCandidateUseCase.execute(UUID.fromString(idCandidate.toString()));
			return ResponseEntity.ok().body(profile);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/jobs")
	@PreAuthorize("hasRole('CANDIDATE')")
	@Tag(name = "Candidate", description = "Endpoints for managing candidates")
	@Operation(summary = "Find jobs by filter", description = "Find jobs by filter")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
			})
	})
	public List<JobEntity> findJobByFilter(@RequestParam String filter) {
		return this.listAllJobsByFilterUseCase.execute(filter);
	}
}
