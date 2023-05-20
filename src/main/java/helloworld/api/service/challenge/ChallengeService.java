package helloworld.api.service.challenge;

import helloworld.api.domain.Users;
import helloworld.api.dto.ChallengeRequestDTO;
import helloworld.api.dto.DailyJackpotRollsDTO;

public interface ChallengeService {
    DailyJackpotRollsDTO acceptOrRejectChallenge(Users user, ChallengeRequestDTO challengeRequestDTO);
}
