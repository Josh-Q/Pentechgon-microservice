package helloworld.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ChallengeRequestDTO {
    private boolean hasGaveUp;
    private List<Integer> amountsToSave;
    @AssertTrue(message = "Amounts to save should be more than 0 and have 5 rolls")
	public boolean isAmountsToSaveValid() {
        return amountsToSave.stream().allMatch(amountToSave -> amountToSave != null && amountToSave > 0) && amountsToSave.size() == 5;
	}
}
