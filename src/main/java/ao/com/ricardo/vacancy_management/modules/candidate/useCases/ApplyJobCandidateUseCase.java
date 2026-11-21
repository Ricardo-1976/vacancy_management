package ao.com.ricardo.vacancy_management.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.com.ricardo.vacancy_management.exeptions.CandidateNotFoundException;
import ao.com.ricardo.vacancy_management.exeptions.JobNotFoundException;
import ao.com.ricardo.vacancy_management.modules.candidate.entities.ApplyJobEntity;
import ao.com.ricardo.vacancy_management.modules.candidate.repositories.ApplyJobRepository;
import ao.com.ricardo.vacancy_management.modules.candidate.repositories.CandidateRepository;
import ao.com.ricardo.vacancy_management.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new CandidateNotFoundException();
                });

        this.jobRepository.findById(idJob)
                .orElseThrow(() -> {
                    throw new JobNotFoundException();
                });

        var applyJob = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob).build();

        return this.applyJobRepository.save(applyJob);

    }

}
