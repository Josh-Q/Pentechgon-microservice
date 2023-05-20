package helloworld.api.service.user;

import helloworld.api.domain.Users;
import helloworld.api.dto.LoginRequest;
import helloworld.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Users> validateUser(LoginRequest loginRequest) {
        Optional<Users> optionalUser = userRepository.findByName(loginRequest.getUsername());
       return optionalUser.isPresent() && optionalUser.get().getPassword().equals(loginRequest.getPassword())
               ? optionalUser : Optional.empty();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Users> findAll() {
       return userRepository.findAll();
    }
}
