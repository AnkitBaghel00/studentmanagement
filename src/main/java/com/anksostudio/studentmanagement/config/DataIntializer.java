package com.anksostudio.studentmanagement.config;


import com.anksostudio.studentmanagement.model.Users;
import com.anksostudio.studentmanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataIntializer {

    @Bean
    CommandLineRunner loadSampleData(UserRepository userRepository, PasswordEncoder passwordEncoder){

        return args -> {
            if(!userRepository.existsByUsername("Admin")) {
                Users users = new Users();
                users.setUsername("Admin");
                users.setPassword(passwordEncoder.encode("admin@123"));
                users.setActive(true);
                userRepository.save(users);
            }

        };
    }
}
