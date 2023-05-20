package helloworld.api.service.user;

import helloworld.api.domain.Users;
import helloworld.api.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Users> validateUser(LoginRequest loginRequest);
    Optional<Users> findById(Long id);

    List<Users> findAll();
}
