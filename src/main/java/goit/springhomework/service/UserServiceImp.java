package goit.springhomework.service;

import goit.springhomework.model.Role;
import goit.springhomework.model.User;
import goit.springhomework.repository.RoleRepository;
import goit.springhomework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(roleRepository.findAll()));
        userRepository.save(user);

    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findBy(username);
    }
}
