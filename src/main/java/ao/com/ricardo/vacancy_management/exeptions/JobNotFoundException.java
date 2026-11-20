package ao.com.ricardo.vacancy_management.exeptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Job not found!");
    }
}
