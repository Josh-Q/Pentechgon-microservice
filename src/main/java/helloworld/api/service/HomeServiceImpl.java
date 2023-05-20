package helloworld.api.service;

import helloworld.api.domain.Users;
import helloworld.api.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class HomeServiceImpl implements HomeService {

    private HomeRepository homeRepository;

    @Autowired
    public HomeServiceImpl(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public List<Users> findAll() {
       return homeRepository.findAll();
    }
}
