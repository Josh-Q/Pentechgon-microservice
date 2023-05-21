package helloworld.api.dto;


import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import org.mapstruct.*;
import org.springframework.validation.annotation.Validated;


@Validated
@Mapper(componentModel = "spring")
public interface LoginMapper {


    @Mapping(source = "users",target = "usersDTO")
    LoginResponseDTO toLoginResponseDTO(Users users, String token);

//    @Mapping(source = "savingsHistory",target = "summaryResponseDTO",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
//    UsersDTO toUsersDTO(Users users);

    @Mapping(source = "savingsHistory", target = "summaryResponseDTO", defaultExpression = "java(toSummaryResponseDTO(users.getSavingsHistory()))")
    UsersDTO toUsersDTO(Users users);

    default SummaryResponseDTO toSummaryResponseDTO(SavingsHistory savingsHistory) {
        SummaryResponseDTO summaryResponseDTO = new SummaryResponseDTO();

        if (savingsHistory == null) {
            summaryResponseDTO.setMaxStreakSavings(0);
            summaryResponseDTO.setCurrentStreakSavings(0);
            summaryResponseDTO.setMaxStreakDays(0);
            summaryResponseDTO.setCurrentStreakDays(0);
            summaryResponseDTO.setTotalSavings(0);
        }
        else{
            summaryResponseDTO.setMaxStreakSavings(savingsHistory.getMaxStreakSavings());
            summaryResponseDTO.setCurrentStreakSavings(savingsHistory.getCurrentStreakSavings());
            summaryResponseDTO.setMaxStreakDays(savingsHistory.getMaxStreakDays());
            summaryResponseDTO.setCurrentStreakDays(savingsHistory.getCurrentStreakDays());
            summaryResponseDTO.setTotalSavings(savingsHistory.getTotalSavings());
        }
        return summaryResponseDTO;
    }


//    @Mappings({
//            @Mapping(target = "maxStreakSavings", expression = "java(savingsHistory != null ? savingsHistory.getMaxStreakSavings() : 0)"),
//            @Mapping(target = "currentStreakSavings", expression = "java(savingsHistory != null ? savingsHistory.getCurrentStreakSavings() : 0)"),
//            @Mapping(target = "maxStreakDays", expression = "java(savingsHistory != null ? savingsHistory.getMaxStreakDays() : 0)"),
//            @Mapping(target = "currentStreakDays", expression = "java(savingsHistory != null ? savingsHistory.getCurrentStreakDays() : 0)"),
//            @Mapping(target = "totalSavings", expression = "java(savingsHistory != null ? savingsHistory.getTotalSavings() : 0)")
//    })
//    SummaryResponseDTO toSummaryResponseDTO(SavingsHistory savingsHistory);

}
