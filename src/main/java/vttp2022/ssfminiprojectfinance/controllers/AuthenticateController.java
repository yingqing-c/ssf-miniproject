package vttp2022.ssfminiprojectfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.ssfminiprojectfinance.models.User;
import vttp2022.ssfminiprojectfinance.services.UserRedis;

@RestController
@RequestMapping
public class AuthenticateController {

    @Autowired
    private UserRedis service;
    
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        User u = service.findByEmail(user.getEmail());
        if (u != null) {
            return ResponseEntity.status(HttpStatus.OK)
                                .body("This email already exists");
        }
        int x = service.save(user);
        if (x == 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                                .body("Successfully signed up");
        }
        return ResponseEntity.status(500).body("Failed to sign up");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody User user) {
        User u = service.findByEmail(user.getEmail());
        if (u != null && u.getPassword().equals(user.getPassword())) {
            u.setLoggedIn(true);
            return ResponseEntity.ok().body("Logged in");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Wrong password");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> signout(@RequestBody User user) {
        User u = service.findByEmail(user.getEmail());
        if (u != null) {
           u.setLoggedIn(false);
           return ResponseEntity.ok().body("Logged out");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");        
    }
}
