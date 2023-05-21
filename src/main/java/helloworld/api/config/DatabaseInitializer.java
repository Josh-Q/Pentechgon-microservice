package helloworld.api.config;

import helloworld.api.domain.Users;
import helloworld.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private UserRepository userRepository;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Code to insert data into the database
        userRepository.deleteAll();


        Users user1 = new Users("John", "HAHA1", false, null, null);
        Users user2 = new Users("Doe", "HAHA1", false, null, null);
        Users user3 = new Users("Jane", "HAHA1", false, null, null);
        Users user4 = new Users("Smith", "HAHA1", false, null, null);
//        Users user1 = new Users("John", "HAHA1",false);
//        Users user2 = new Users("Doe", "HAHA1",false);
//        Users user3 = new Users("Jane", "HAHA1",false);
//        Users user4 = new Users("Smith", "HAHA1",false);
        List<Users> arr = new ArrayList<Users>() {{
            add(user1);
            add(user2);
            add(user3);
            add(user4);
        }};
        userRepository.saveAll(arr);
    }

}

