package ao.com.ricardo.vacancy_management.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ao.com.ricardo.vacancy_management.modules.candidate.dto.ProfileCandidateRequestDTO;
import ao.com.ricardo.vacancy_management.modules.candidate.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateRequestDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("User not found");
        });

    var candidateDTO = ProfileCandidateRequestDTO.builder()
        .id(candidate.getId())
        .name(candidate.getName())
        .email(candidate.getEmail())
        .username(candidate.getUsername())
        .description(candidate.getDescription())
        .build();

    return candidateDTO;
  }
}
