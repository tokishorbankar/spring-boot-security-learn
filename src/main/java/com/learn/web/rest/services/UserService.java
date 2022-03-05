package com.learn.web.rest.services;

import com.learn.web.rest.modules.UserModule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<UserModule> userModuleList = new ArrayList<>();

    public UserService() {
        loadData();
    }

    private void loadData() {
        userModuleList.addAll(List.of(
                UserModule
                        .builder()
                        ._id(1l)
                        .username("kishor")
                        .password("kishor")
                        .email("kishor@gamil.com")
                        .build(),
                UserModule
                        .builder()
                        ._id(2l)
                        .username("bankar")
                        .password("bankar")
                        .email("bankar@gamil.com")
                        .build()
        ));
    }


    /**
     * get UserModule
     *
     * @param username
     * @return UserModule
     */
    public Optional<UserModule> findByUsername(final String username) {

        return Optional.ofNullable(userModuleList.parallelStream().filter(userModule -> userModule.getUsername().equalsIgnoreCase(username.trim())).findFirst().orElseThrow(() -> new RuntimeException("user does not exist")));

    }

    /**
     * Save UserModule
     *
     * @param userModule
     * @return Saved UserModule
     */
    public Optional<UserModule> save(final UserModule userModule) {

        Optional<UserModule> userModuleOptional = userModuleList.parallelStream().filter(user -> user.getUsername().equalsIgnoreCase(user.getUsername())).findAny();
        if (userModuleOptional.isPresent()) {
            throw new RuntimeException(String.format("a user already exists with that username %s", userModule.getUsername()));
        }
        userModuleList.add(userModule);
        return findByUsername(userModule.getUsername());

    }

    /**
     * get UserModules
     *
     * @return List<UserModule> UserModule</UserModule>
     */
    public List<UserModule> findAll() {

        return userModuleList;

    }

}
