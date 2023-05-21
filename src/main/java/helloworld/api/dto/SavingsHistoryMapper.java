package helloworld.api.dto;


import helloworld.api.domain.DailyJackpotRolls;
import helloworld.api.domain.JackpotRollValues;
import helloworld.api.domain.SavingsHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;


@Validated
@Mapper(componentModel = "spring")
public interface SavingsHistoryMapper {
    SummaryResponseDTO toSummaryResponseDTO(SavingsHistory savingsHistory);
}
