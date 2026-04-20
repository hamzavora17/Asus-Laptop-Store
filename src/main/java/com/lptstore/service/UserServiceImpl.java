package com.lptstore.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lptstore.entity.user;
import com.lptstore.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUsers(user user) {
        userRepository.save(user);
    }

    @Override
    public List<user> getAllUsersList() {
        return userRepository.findAll();
    }

    @Override
    public boolean checkLogin(String email, String password) {

        java.util.List<user> users = userRepository.findByEmail(email);

        if (users.isEmpty()) return false;

        user u = users.get(0);

        System.out.println("ENTERED: " + password);
        System.out.println("DB: " + u.getPassword());

        return u.getPassword().trim().equals(password.trim());
    }
    @Override
    public user getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String resetPassword(String email) {

        java.util.List<user> users = userRepository.findByEmail(email);

        // ✅ random password (8 char)
        String newPassword = java.util.UUID.randomUUID().toString().substring(0, 8);

        if (!users.isEmpty()) {
            user u = users.get(0); // sirf first user use karo
            u.setPassword(newPassword);
            userRepository.save(u);
        } else {
            user newUser = new user();
            newUser.setEmail(email);
            newUser.setPassword(newPassword);
            userRepository.save(newUser);
        }

        System.out.println("NEW PASSWORD: " + newPassword); // debug

        return newPassword;
    }
}