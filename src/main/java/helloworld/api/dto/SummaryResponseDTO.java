package helloworld.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.validation.constraints.AssertTrue;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class SummaryResponseDTO {
    private Integer maxStreakSavings;
    private Integer currentStreakSavings;
    private Integer maxStreakDays;
    private Integer currentStreakDays;
    private Integer totalSavings;
}
