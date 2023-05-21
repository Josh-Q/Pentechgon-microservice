package helloworld.api.controller;

//import javax.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequest;
import helloworld.api.domain.Users;
import helloworld.api.dto.GenericItemResponse;
import helloworld.api.dto.LoginMapper;
import helloworld.api.dto.LoginRequest;
import helloworld.api.dto.LoginResponseDTO;
import helloworld.api.jwt.JwtTokenConfigsService;
import helloworld.api.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;


@Controller
@RequestMapping("/api/v1/login")
public class LoginController {

    private final UserService userService;
    private final JwtTokenConfigsService jwtTokenConfigsService;
    private final LoginMapper loginMapper;
    // check CSRF
//    private final LoggedInUserService loggedInUserService;

    @Autowired
    public LoginController(UserService userService, JwtTokenConfigsService jwtTokenConfigsService, LoginMapper loginMapper) {
        this.userService = userService;
        this.jwtTokenConfigsService = jwtTokenConfigsService;
        this.loginMapper = loginMapper;
    }

    @GetMapping("")
    public ResponseEntity<GenericItemResponse> getUserOne() {
        GenericItemResponse response = new GenericItemResponse();

        Optional<Users> optionalUser =  userService.findAll().stream().findFirst();

        if(optionalUser.isPresent()){
            Users user = optionalUser.get();
            String token = jwtTokenConfigsService.generateToken(user.getId());
            response.setData(token);
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            response.setMessage("get user 1 passed");
            return new ResponseEntity<>(response, HttpStatus.OK);
        };
        response.setMessage("get user 1 failed");
        return new ResponseEntity<>(response, UNAUTHORIZED);
    }


    @PostMapping("")
    public ResponseEntity<GenericItemResponse> postLogin(@RequestBody LoginRequest loginRequest) {
        GenericItemResponse response = new GenericItemResponse();

       Optional<Users> optionalUser =  userService.validateUser(loginRequest);

        if(optionalUser.isPresent()){
            Users user = optionalUser.get();
            String token = jwtTokenConfigsService.generateToken(user.getId());

//            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(user,token);
            response.setData(loginMapper.toLoginResponseDTO(user,token));
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            response.setMessage("Login passed");
            return new ResponseEntity<>(response, HttpStatus.OK);
        };
        response.setMessage("Login failed");
        return new ResponseEntity<>(response, UNAUTHORIZED);
     }
}
