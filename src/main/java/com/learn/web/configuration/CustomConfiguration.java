package com.learn.web.configuration;

import com.learn.web.rest.entities.UserEntity;
import com.learn.web.rest.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class CustomConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostConstruct
    public void loadData() {
        List<UserEntity> userEntityList = List.of(
                UserEntity
                        .builder()
                        .username("kishor")
                        .password("kishor")
                        .email("kishor@gamil.com")
                        .build(),
                UserEntity
                        .builder()
                        .username("bankar")
                        .password("bankar")
                        .email("bankar@gamil.com")
                        .build()
        );

        userRepository.saveAll(userEntityList);
    }
}
