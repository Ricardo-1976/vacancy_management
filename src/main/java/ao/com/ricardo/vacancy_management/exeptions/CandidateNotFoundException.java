package ao.com.ricardo.vacancy_management.exeptions;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException() {
        super("Candidate not found!");
    }
    
}
