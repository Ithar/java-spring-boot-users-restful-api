package com.malik.ithar.bootstrap;

import com.malik.ithar.api.v1.domain.User;
import com.malik.ithar.api.v1.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner{

    private final UserRepository userRepository;

    @Autowired
    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {

        // User 1
        User user1 = User.builder()
            .firstName("Martin")
            .lastName("Heidegger")
            .url("https://en.wikipedia.org/wiki/Martin_Heidegger")
            .build();
        userRepository.save(user1);

        // User 2
        User user2 = User.builder()
            .firstName("Jean-Paul")
            .lastName("Sartre")
            .url("https://en.wikipedia.org/wiki/Jean-Paul_Sartre")
            .build();
        userRepository.save(user2);

        // User 3
        User user3 = User.builder()
            .firstName("Immanuel")
            .lastName("Kant")
            .url("https://en.wikipedia.org/wiki/Immanuel_Kant")
            .build();
        userRepository.save(user3);

        log.info("##########################");
        log.info("TOTAL USERS CREATED '{}' :", userRepository.count());
        log.info("##########################");
    }
}
