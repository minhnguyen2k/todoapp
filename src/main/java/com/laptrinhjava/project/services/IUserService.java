package com.laptrinhjava.project.services;

import com.laptrinhjava.project.entities.User;
public interface IUserService {
    User save(User user);
    User findOneByUsername(String username);
}
