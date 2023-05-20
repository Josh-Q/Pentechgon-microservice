package helloworld.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class DailyJackpotRollsDTO {
    private Long id;
    private Timestamp createdAt;
    private List<JackpotRollValuesDTO> jackpotRollValuesDTOList;
}
