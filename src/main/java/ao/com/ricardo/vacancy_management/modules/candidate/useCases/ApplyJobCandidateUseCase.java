package ao.com.ricardo.vacancy_management.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.com.ricardo.vacancy_management.exeptions.CandidateNotFoundException;
import ao.com.ricardo.vacancy_management.exeptions.JobNotFoundException;
import ao.com.ricardo.vacancy_management.modules.candidate.repositories.CandidateRepository;
import ao.com.ricardo.vacancy_management.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired 
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    public void execute( UUID idCandidate, UUID idJob) {

        this.candidateRepository.findById(idCandidate)
            .orElseThrow(() -> {
                throw new CandidateNotFoundException();
            });
       
        this.jobRepository.findById(idJob)
            .orElseThrow(() -> {
                throw new JobNotFoundException();
            });
    }
    
}
