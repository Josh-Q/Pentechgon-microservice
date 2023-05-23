package helloworld.api.controller;

//import javax.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequest;

import helloworld.api.domain.Users;
import helloworld.api.dto.GenericItemResponse;
import helloworld.api.exception.CustomTokenException;
import helloworld.api.jwt.JwtTokenConfigsService;
import helloworld.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Controller
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenConfigsService jwtTokenConfigsService;
    // check CSRF
//    private final LoggedInUserService loggedInUserService;

    @Autowired
    public UserController(UserService userService, JwtTokenConfigsService jwtTokenConfigsService) {
        this.userService = userService;
        this.jwtTokenConfigsService = jwtTokenConfigsService;
    }

//    @GetMapping("/all")
//    public ResponseEntity<GenericItemResponse> getAllUsers() {
//        GenericItemResponse response = new GenericItemResponse();
//
//        List<Users> users =  userService.findAll();
//        response.setData(users);
//        response.setMessage("All users");
//        return new ResponseEntity<>(response, HttpStatus.OK);
//     }

    @GetMapping("/jackpot")
    public ResponseEntity<GenericItemResponse> getJackpotState(HttpServletRequest request) {
        GenericItemResponse response = new GenericItemResponse();
        try{
            Users user = jwtTokenConfigsService.verifyToken(request);
            response.setData(user.isHasRolledToday());
            response.setMessage("User's current roll state");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (CustomTokenException e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, UNAUTHORIZED);
        }
     }
}
