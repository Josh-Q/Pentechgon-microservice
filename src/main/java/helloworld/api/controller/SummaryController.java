package helloworld.api.controller;

import helloworld.api.domain.Users;
import helloworld.api.dto.ChallengeRequestDTO;
import helloworld.api.dto.GenericItemResponse;
import helloworld.api.exception.CustomTokenException;
import helloworld.api.jwt.JwtTokenConfigsService;
import helloworld.api.service.challenge.ChallengeService;
import helloworld.api.service.savingshistory.SavingsHistoryService;
import helloworld.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Controller
@RequestMapping("/api/v1/summary")
@Validated
public class SummaryController {
    private final SavingsHistoryService savingsHistoryService;
    private final JwtTokenConfigsService jwtTokenConfigsService;

    @Autowired
    public SummaryController(SavingsHistoryService savingsHistoryService, JwtTokenConfigsService jwtTokenConfigsService) {
        this.savingsHistoryService = savingsHistoryService;
        this.jwtTokenConfigsService = jwtTokenConfigsService;
    }

    @GetMapping("")
    public ResponseEntity<GenericItemResponse> getSummary(HttpServletRequest request) {

        GenericItemResponse response = new GenericItemResponse();
        try{
            Users user = jwtTokenConfigsService.verifyToken(request);
            response.setData(savingsHistoryService.findSummaryByUserId(user.getId()));
            response.setMessage("Challenge summary");
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
