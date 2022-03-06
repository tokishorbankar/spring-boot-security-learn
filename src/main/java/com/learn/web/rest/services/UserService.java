package com.learn.web.rest.services;

import com.learn.web.rest.entities.UserEntity;
import com.learn.web.rest.exception.DataBaseTransactionException;
import com.learn.web.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(final Long id) {
        return userRepository.findById(id);
    }

    public Page<UserEntity> findAll(final Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserEntity saveOrUpdate(UserEntity entity) throws DataBaseTransactionException {
        try {
            return userRepository.save(entity);
        } catch (Exception e) {

            throw new DataBaseTransactionException();
        }
    }

    public void deleteById(Long id) throws DataBaseTransactionException {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {

            throw new DataBaseTransactionException(id);
        }
    }

}
