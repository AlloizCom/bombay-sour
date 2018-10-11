package com.bombaysour.bombaysour.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @PostMapping("/login")
    private ResponseEntity<Boolean> login(@RequestBody User user) {
        LOGGER.info("---------------------------User---------------------------");
        if (user.username.equals("admin")) {
            if (user.password.equals("admin")) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    static class User {
        private String username;
        private String password;

        public User() {
        }

        public String getUsername() {
            return username;
        }

        public User setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public User setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
