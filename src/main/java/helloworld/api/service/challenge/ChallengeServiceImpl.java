package helloworld.api.service.challenge;

import helloworld.api.config.TimeHandler;
import helloworld.api.domain.DailyJackpotRolls;
import helloworld.api.domain.JackpotRollValues;
import helloworld.api.domain.SavingsHistory;
import helloworld.api.domain.Users;
import helloworld.api.dto.*;
import helloworld.api.repository.UserRepository;
import helloworld.api.service.savingshistory.SavingsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final UserRepository userRepository;
    private final SavingsHistoryService savingsHistoryService;
    private final DailyJackpotRollsMapper dailyJackpotRollsMapper;

    @Autowired
    public ChallengeServiceImpl(UserRepository userRepository, SavingsHistoryService savingsHistoryService, DailyJackpotRollsMapper dailyJackpotRollsMapper) {
        this.userRepository = userRepository;
        this.savingsHistoryService = savingsHistoryService;
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


            SavingsHistory previousSavingsHistory = user.getSavingsHistory();

//            SavingsHistory updatedSavingsHistory = updateSavingsHistory(user,challengeRequestDTO);
            SavingsHistory updatedSavingsHistory = savingsHistoryService.updateSavingsHistory(user,challengeRequestDTO);

            try{

                DailyJackpotRolls dailyJackpotRolls = new DailyJackpotRolls();
                dailyJackpotRolls.setUser(user);
                dailyJackpotRolls.setCreatedAt(Timestamp.valueOf(TimeHandler.now()));

                List<JackpotRollValues> jackpotRollValuesList = new ArrayList<>();
                for (JackpotRollValues value : jackpotRollValues) {
                    JackpotRollValues jackpotRollValue = new JackpotRollValues();
                    jackpotRollValue.setDailyJackpotRolls(dailyJackpotRolls);
                    jackpotRollValue.setValue(value.getValue());
                    jackpotRollValuesList.add(jackpotRollValue);
                }

                dailyJackpotRolls.setJackpotRollValues(jackpotRollValuesList);

                user.getDailyJackpotRolls().add(dailyJackpotRolls);
                user.setHasRolledToday(true);

                Users savedUser = userRepository.save(user);

                DailyJackpotRolls savedDailyJackpotRolls = savedUser.getDailyJackpotRolls().stream()
                    .max(Comparator.comparing(DailyJackpotRolls::getCreatedAt))
                    .orElse(null);

            return dailyJackpotRollsMapper.toDailyJackpotRollsDTO(savedDailyJackpotRolls);
            }
            catch (Exception e){
//                undoSavingsHistory(updatedSavingsHistory,previousSavingsHistory);
                savingsHistoryService.undoSavingsHistory(updatedSavingsHistory,previousSavingsHistory);

                throw new IllegalArgumentException("Failed to update savings history");
            }
    }

//    private void undoSavingsHistory(SavingsHistory savingsHistory,SavingsHistory previousSavingsHistory){
//
//        savingsHistory.setMaxStreakSavings(previousSavingsHistory.getMaxStreakSavings());
//        savingsHistory.setCurrentStreakSavings(previousSavingsHistory.getCurrentStreakSavings());
//        savingsHistory.setMaxStreakDays(previousSavingsHistory.getMaxStreakDays());
//        savingsHistory.setCurrentStreakDays(previousSavingsHistory.getCurrentStreakDays());
//        savingsHistory.setTotalSavings(previousSavingsHistory.getTotalSavings());
//
//        return savingsHistoryRepository.save(savingsHistory);
//    }
//
//    private SavingsHistory updateSavingsHistory(Users user, ChallengeRequestDTO challengeRequestDTO) {
//        int sumOfRolls = challengeRequestDTO.getAmountsToSave().stream().mapToInt(Integer::intValue).sum();
//        boolean hasGiveUp = challengeRequestDTO.isHasGaveUp();
//
//        SavingsHistory savingsHistory = user.getSavingsHistory();
//
//        if (savingsHistory == null) {
//            savingsHistory = new SavingsHistory();
//            user.setSavingsHistory(savingsHistory);
//        }
//
//        if (hasGiveUp) {
//            savingsHistory.setCurrentStreakSavings(0);
//            savingsHistory.setCurrentStreakDays(0);
//        } else {
//            int updatedCurrentStreakDays = savingsHistory.getCurrentStreakDays() + 1;
//            int updatedCurrentStreakSavings = savingsHistory.getCurrentStreakSavings() + sumOfRolls;
//            int maxStreakSavings = Math.max(updatedCurrentStreakSavings, savingsHistory.getMaxStreakSavings());
//            int maxStreakDays = Math.max(updatedCurrentStreakDays, savingsHistory.getMaxStreakDays());
//
//            savingsHistory.setCurrentStreakDays(updatedCurrentStreakDays);
//            savingsHistory.setCurrentStreakSavings(updatedCurrentStreakSavings);
//            savingsHistory.setMaxStreakSavings(maxStreakSavings);
//            savingsHistory.setMaxStreakDays(maxStreakDays);
//            savingsHistory.setTotalSavings(savingsHistory.getTotalSavings() + sumOfRolls);
//        }
//        return savingsHistoryRepository.save(savingsHistory);
//    }


}
