package helloworld.api.dto;


import helloworld.api.domain.DailyJackpotRolls;
import helloworld.api.domain.JackpotRollValues;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Validated
@Mapper(componentModel = "spring")
public interface DailyJackpotRollsMapper {

    @Mapping(target = "id",source = "id")
    @Mapping(target = "createdAt",source = "createdAt")
    @Mapping(target = "jackpotRollValuesDTOList",source = "jackpotRollValues")
    DailyJackpotRollsDTO toDailyJackpotRollsDTO(DailyJackpotRolls dailyJackpotRolls);

    JackpotRollValuesDTO toJackpotRollValuesDTO(JackpotRollValues jackpotRollValue);

}
