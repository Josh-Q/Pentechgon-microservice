package helloworld.api.service.savingshistory;

import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import helloworld.api.dto.*;
import helloworld.api.repository.SavingsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class SavingsHistoryServiceImpl implements SavingsHistoryService {

    private final SavingsHistoryRepository savingsHistoryRepository;

    private final SavingsHistoryMapper savingsHistoryMapper;
    @Autowired
    public SavingsHistoryServiceImpl(SavingsHistoryRepository savingsHistoryRepository, SavingsHistoryMapper savingsHistoryMapper) {
        this.savingsHistoryRepository = savingsHistoryRepository;
        this.savingsHistoryMapper = savingsHistoryMapper;
    }

    @Override
    public SavingsHistory undoSavingsHistory(SavingsHistory savingsHistory,SavingsHistory previousSavingsHistory){

        savingsHistory.setMaxStreakSavings(previousSavingsHistory.getMaxStreakSavings());
        savingsHistory.setCurrentStreakSavings(previousSavingsHistory.getCurrentStreakSavings());
        savingsHistory.setMaxStreakDays(previousSavingsHistory.getMaxStreakDays());
        savingsHistory.setCurrentStreakDays(previousSavingsHistory.getCurrentStreakDays());
        savingsHistory.setTotalSavings(previousSavingsHistory.getTotalSavings());

        return savingsHistoryRepository.save(savingsHistory);
    }

    @Override
    public SavingsHistory updateSavingsHistory(Users user, ChallengeRequestDTO challengeRequestDTO) {
        int sumOfRolls = challengeRequestDTO.getAmountsToSave().stream().mapToInt(Integer::intValue).sum();
        boolean hasGiveUp = challengeRequestDTO.isHasGaveUp();

        SavingsHistory savingsHistory = user.getSavingsHistory();

        if (savingsHistory == null) {
            savingsHistory = new SavingsHistory(user);
        }

        if (hasGiveUp) {
            savingsHistory.setCurrentStreakSavings(0);
            savingsHistory.setCurrentStreakDays(0);
        } else {
            int updatedCurrentStreakDays = savingsHistory.getCurrentStreakDays() + 1;
            int updatedCurrentStreakSavings = savingsHistory.getCurrentStreakSavings() + sumOfRolls;
            int maxStreakSavings = Math.max(updatedCurrentStreakSavings, savingsHistory.getMaxStreakSavings());
            int maxStreakDays = Math.max(updatedCurrentStreakDays, savingsHistory.getMaxStreakDays());

            savingsHistory.setCurrentStreakDays(updatedCurrentStreakDays);
            savingsHistory.setCurrentStreakSavings(updatedCurrentStreakSavings);
            savingsHistory.setMaxStreakSavings(maxStreakSavings);
            savingsHistory.setMaxStreakDays(maxStreakDays);
            savingsHistory.setTotalSavings(savingsHistory.getTotalSavings() + sumOfRolls);
        }
        return savingsHistoryRepository.save(savingsHistory);
    }

    @Override
    public SummaryResponseDTO findSummaryByUserId(Long userId) {
       Optional<SavingsHistory> savingsHistoryOptional =  savingsHistoryRepository.findByUserId(userId);
      return savingsHistoryOptional.isPresent() ? savingsHistoryMapper.toSummaryResponseDTO(savingsHistoryOptional.get()): new SummaryResponseDTO(0,0,0,0,0);
    }
}
