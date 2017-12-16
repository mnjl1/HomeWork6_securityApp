package goit.springhomework.service;

import goit.springhomework.model.User;

public interface UserService {
    void save(User user);

    User findByUserName(String username);
}
