package helloworld.api.service.savingshistory;

import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import helloworld.api.dto.ChallengeRequestDTO;
import helloworld.api.dto.DailyJackpotRollsDTO;
import helloworld.api.dto.SummaryResponseDTO;

public interface SavingsHistoryService {
    SavingsHistory undoSavingsHistory(SavingsHistory savingsHistory, SavingsHistory previousSavingsHistory);
    SavingsHistory updateSavingsHistory(Users user, ChallengeRequestDTO challengeRequestDTO);
    SummaryResponseDTO findSummaryByUserId(Long userId);
}
