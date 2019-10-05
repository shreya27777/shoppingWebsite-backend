package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UsersServiceImp implements UsersService {
    public UsersRepository usersRepository;
    private Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public String loginUsers(String email, String password) {
        if (usersRepository.existsByEmailAndPassword(email, password)) {
            return "you are logged in";
        }
        return "invalid credentials";
    }

    public String signUpUser(Users users) {
        if (!usersRepository.existsByEmail(users.getEmail())) {
            usersRepository.saveAndFlush(users);
            return "signed in";
        }
        return "error signing in";
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

    public String getUser(String email) {
        Optional<Users> user = usersRepository.existsByEmail(email);
        if (user.isPresent()) {
            LOGGER.log(Level.INFO,user.get().getEmail());
            return user.get().toString();
        }
        return "User Not Found";
    }
}
