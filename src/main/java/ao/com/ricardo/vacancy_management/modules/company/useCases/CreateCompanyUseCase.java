package ao.com.ricardo.vacancy_management.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ao.com.ricardo.vacancy_management.exeptions.UserFoundException;
import ao.com.ricardo.vacancy_management.modules.company.entities.CompanyEntity;
import ao.com.ricardo.vacancy_management.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

	@Autowired
	private CompanyRepository companyRepository;

	public CompanyEntity execute(CompanyEntity companyEntity) {

		this.companyRepository
		.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
		.ifPresent((user) -> {
			throw new UserFoundException();
		});

    	return this.companyRepository.save(companyEntity);
	}
	
}
