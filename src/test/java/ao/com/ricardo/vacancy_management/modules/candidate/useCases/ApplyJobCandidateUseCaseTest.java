package ao.com.ricardo.vacancy_management.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ao.com.ricardo.vacancy_management.exeptions.CandidateNotFoundException;
import ao.com.ricardo.vacancy_management.exeptions.JobNotFoundException;
import ao.com.ricardo.vacancy_management.modules.candidate.entities.CandidateEntity;
import ao.com.ricardo.vacancy_management.modules.candidate.repositories.CandidateRepository;
import ao.com.ricardo.vacancy_management.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    
    @InjectMocks 
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock 
    private JobRepository jobRepository;

    @Test
    @DisplayName("should not be able to apply job with candidate not found")
    public void candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);

        } catch (Exception e) {
            assertThat(e).isInstanceOf(CandidateNotFoundException.class);
            
        }
    }

    @Test
    @DisplayName("should not be able to apply job with job not found")
    public void job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
        
        try {
            applyJobCandidateUseCase.execute(idCandidate, null);

        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
