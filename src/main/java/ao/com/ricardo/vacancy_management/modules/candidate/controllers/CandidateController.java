package ao.com.ricardo.vacancy_management.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ao.com.ricardo.vacancy_management.modules.candidate.CandidateEntity;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

  @PostMapping("/")
  public void create( @RequestBody CandidateEntity candidateEntity) {
    System.out.println("Candidato");
    System.out.println(candidateEntity.getEmail());
  }

}
