package helloworld.api.controller;

//import javax.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequest;

import helloworld.api.domain.Users;
import helloworld.api.dto.ChallengeRequestDTO;
import helloworld.api.dto.GenericItemResponse;
import helloworld.api.exception.CustomTokenException;
import helloworld.api.jwt.JwtTokenConfigsService;
import helloworld.api.service.challenge.ChallengeService;
import helloworld.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Controller
@RequestMapping("/api/v1/challenge")
@Validated
public class ChallengeController {

    private final UserService userService;
    private final ChallengeService challengeService;
    private final JwtTokenConfigsService jwtTokenConfigsService;
    // check CSRF
//    private final LoggedInUserService loggedInUserService;


    @Autowired
    public ChallengeController(UserService userService, ChallengeService challengeService, JwtTokenConfigsService jwtTokenConfigsService) {
        this.userService = userService;
        this.challengeService = challengeService;
        this.jwtTokenConfigsService = jwtTokenConfigsService;
    }

    @PostMapping("")
    public ResponseEntity<GenericItemResponse> acceptOrRejectChallenge(@Valid @RequestBody ChallengeRequestDTO challengeRequestDTO,
                                                                       HttpServletRequest request) {

        GenericItemResponse response = new GenericItemResponse();
        try{
        Users user = jwtTokenConfigsService.verifyToken(request);

        // Limit users from rolling once a day
//        if(user.isHasRolledToday()){
//            response.setMessage("You have already rolled for today !");
//        }
//        else{
            response.setData(challengeService.acceptOrRejectChallenge(user,challengeRequestDTO));
            response.setMessage("Challenge " + (challengeRequestDTO.isHasGaveUp() ? "Failed" : "Accepted" ));
//        }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (CustomTokenException e){
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, UNAUTHORIZED);
    }
        catch (IllegalArgumentException e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response,BAD_REQUEST);
        }


     }
}
