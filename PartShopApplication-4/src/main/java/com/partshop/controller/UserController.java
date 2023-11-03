package com.partshop.controller;

import com.partshop.entity.User;
import com.partshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private boolean isLoggedIn;

    private String loggedInUser;


    @GetMapping("/loggedin")
    public boolean checkLoggedIn() {
        return userService.isLoggedIn();

        //SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public User loginUser(@RequestBody User user) {

        User dbUser = userService.getUser(user.getID());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {

            userService.setLoggedIn(true);
            userService.setLoggedInUser(dbUser.getID());
            return dbUser;
        }
        userService.setLoggedIn(false);
        userService.setLoggedInUser(null);
        return null;
    }

    @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody User user) {
        Map<String, Object> hmap = new HashMap<String, Object>();
        if (userService.save(user) != null) {
            isLoggedIn = true;
            loggedInUser = user.getID();


            hmap.put("response", "Successfully signed up");

            return new ResponseEntity<>(hmap, HttpStatus.CREATED);

        }
        isLoggedIn = false;
        loggedInUser = null;
        hmap.put("response", "Try again");
        return new ResponseEntity<>(hmap, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = "application/json")
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }


}
