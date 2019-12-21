package com.arobs.meetups;

import com.arobs.meetups.service.user.UserServiceImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArobsMeetupsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImplementation.class);


    public static void main(String[] args) {
        SpringApplication.run(ArobsMeetupsApplication.class, args);
    }

}
