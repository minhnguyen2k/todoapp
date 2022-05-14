package com.laptrinhjava.project.services.impl;

import com.laptrinhjava.project.entities.User;
import com.laptrinhjava.project.repositories.UserRepository;
import com.laptrinhjava.project.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    public User save(User user){
       return userRepository.save(user);
    }
    public User findOneByUsername(String username){
        return userRepository.findOneByUsername(username);
    }
}
