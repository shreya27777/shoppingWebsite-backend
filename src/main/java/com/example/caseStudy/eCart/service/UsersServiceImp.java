package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsersServiceImp implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public String loginUsers(String email, String password) {
        if (usersRepository.existsByEmailAndPassword(email, password)) {
            return "you are logged in";
        }
        return "invalid credentials";
    }

    public Boolean signUpUser(Users users) {
        if (!usersRepository.existsByEmail(users.getEmail())) {
            usersRepository.saveAndFlush(users);
            return true;
        }
        return false;
    }

    public String removeAllUsers() {
        usersRepository.deleteAll();
        return "all users removed";
    }

    public String removeUser(Users users) {
        usersRepository.delete(users);
        return "user removed";
    }

    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUser(String email) {
        boolean isPresent = usersRepository.existsByEmail(email);
        if (isPresent) {
            return usersRepository.findByEmail(email);
        }
        return null;
    }
}
