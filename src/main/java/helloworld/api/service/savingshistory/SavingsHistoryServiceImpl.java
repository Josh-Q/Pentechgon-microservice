package helloworld.api.service.savingshistory;

import helloworld.api.config.TimeHandler;
import helloworld.api.domain.DailyJackpotRolls;
import helloworld.api.domain.JackpotRollValues;
import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import helloworld.api.dto.ChallengeRequestDTO;
import helloworld.api.dto.DailyJackpotRollsDTO;
import helloworld.api.dto.DailyJackpotRollsMapper;
import helloworld.api.repository.SavingsHistoryRepository;
import helloworld.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SavingsHistoryServiceImpl implements SavingsHistoryService {

    private final SavingsHistoryRepository savingsHistoryRepository;

    @Autowired
    public SavingsHistoryServiceImpl(SavingsHistoryRepository savingsHistoryRepository) {
        this.savingsHistoryRepository = savingsHistoryRepository;
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
    public SavingsHistory findByUserId(Long userId) {
        return savingsHistoryRepository.findByUserId(userId);
    }
}
