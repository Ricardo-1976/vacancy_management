package ao.com.ricardo.vacancy_management.exeptions;

public class UserFoundException extends RuntimeException {
	public UserFoundException() {
		super("User already exists!");
	}
}
