package ao.com.ricardo.vacancy_management.exeptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {

  private String field;
  private String message;
}
