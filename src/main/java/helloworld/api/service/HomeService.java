package helloworld.api.service;

import helloworld.api.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HomeService {
    List<Users> findAll();
}
