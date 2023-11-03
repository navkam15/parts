package com.partshop.service;

import com.partshop.entity.User;
import com.partshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private boolean isLoggedIn;
    private String loggedInUser;

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }


    public void delete(String id) {
        userRepository.deleteById(id);
    }

    public User save(User user) {
        if (user.getPassword() == null || user.getID() == null || getUser(user.getID()) != null)
            return null;

        // user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String id) {
        loggedInUser = id;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }


    public void setLoggedIn(boolean b) {
        isLoggedIn = b;
    }

    /*
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }
*/
}
