package helloworld.api.dto;

import helloworld.api.domain.SavingsHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UsersDTO {
    private Long id;
    private String name;
    private boolean hasRolledToday;
    private SummaryResponseDTO summaryResponseDTO;
}
