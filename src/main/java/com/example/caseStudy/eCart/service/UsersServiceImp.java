package com.example.caseStudy.eCart.service;

import com.example.caseStudy.eCart.model.Users;
import com.example.caseStudy.eCart.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
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

    public Users signUpUser(Users users) {
        if (!usersRepository.existsByEmail(users.getEmail())) {
            usersRepository.saveAndFlush(users);
            return users;
        }
        return null;
    }

    public String removeAllUsers() {
        usersRepository.deleteAll();
        return "all users removed";
    }

    public List<Users> removeUser(Long id) {
        Users users1 = usersRepository.findByUserId(id).get();
        usersRepository.delete(users1);
        return usersRepository.findAll();
    }

    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Users getUser(Principal principal) {
        return usersRepository.findByEmail(principal.getName()).get();
    }

    public Users update(Users users, Principal principal) {
        Users updatedUser = usersRepository.findByEmail(principal.getName()).get();
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        updatedUser.setName(users.getName());
        usersRepository.saveAndFlush(updatedUser);
        return updatedUser;
    }

    public List<Users> updateById(Users users, Long id) {
        Users updatedUser = usersRepository.findById(id).get();
        updatedUser.setEmail(users.getEmail());
        updatedUser.setPassword(users.getPassword());
        updatedUser.setName(users.getName());
        usersRepository.saveAndFlush(updatedUser);
        return usersRepository.findAll();
    }
}
