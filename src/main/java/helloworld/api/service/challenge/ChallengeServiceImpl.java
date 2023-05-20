package helloworld.api.service.challenge;

import helloworld.api.config.TimeHandler;
import helloworld.api.domain.DailyJackpotRolls;
import helloworld.api.domain.JackpotRollValues;
import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import helloworld.api.dto.*;
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
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final DailyJackpotRollsMapper dailyJackpotRollsMapper;

    @Autowired
    public ChallengeServiceImpl(UserRepository userRepository, DailyJackpotRollsMapper dailyJackpotRollsMapper) {
        this.userRepository = userRepository;
        this.dailyJackpotRollsMapper = dailyJackpotRollsMapper;
    }

    @Override
    public DailyJackpotRollsDTO acceptOrRejectChallenge(Users user, ChallengeRequestDTO challengeRequestDTO) {

            List<JackpotRollValues> jackpotRollValues = challengeRequestDTO.getAmountsToSave()
                    .stream()
                    .map(x -> {
                        JackpotRollValues jackpotRollValue = new JackpotRollValues();
                        jackpotRollValue.setValue(x);
                        return jackpotRollValue;
                    })
                    .collect(Collectors.toList());

            DailyJackpotRolls dailyJackpotRolls = new DailyJackpotRolls(user, Timestamp.valueOf(TimeHandler.now()), jackpotRollValues);
            user.getDailyJackpotRolls().add(dailyJackpotRolls);
            updateSavingsHistory(user,challengeRequestDTO.getAmountsToSave().stream().mapToInt(Integer::intValue).sum(),true);
            user.setHasRolledToday(true);
            Users users = userRepository.save(user);

        DailyJackpotRolls dailyJackpotRolls1 = users.getDailyJackpotRolls().stream()
                .max(Comparator.comparing(DailyJackpotRolls::getCreatedAt))
                .orElse(null);

            return dailyJackpotRollsMapper.toDailyJackpotRollsDTO(dailyJackpotRolls1);
    }

    private void updateSavingsHistory(Users user, int sumOfRolls, boolean hasGiveUp) {
        SavingsHistory savingsHistory = user.getSavingsHistory();

        if (savingsHistory == null) {
            savingsHistory = new SavingsHistory();
            user.setSavingsHistory(savingsHistory);
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
    }


}
