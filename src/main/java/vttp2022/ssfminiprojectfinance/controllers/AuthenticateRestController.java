package vttp2022.ssfminiprojectfinance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.ssfminiprojectfinance.models.User;
import vttp2022.ssfminiprojectfinance.services.UserRedis;

@RestController
@RequestMapping
public class AuthenticateRestController {

    @Autowired
    private UserRedis service;

    @PostMapping("/api/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        if (user.getEmail() == null || user.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and name must be provided");
        }
        System.out.println("Auth " + user);
        User u = service.findByUsername(user.getUsername());
        if (u != null) {
            return ResponseEntity.status(HttpStatus.OK)
                                .body("This username already exists");
        }

        int x = service.save(user);
        if (x == 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                                .body("Successfully signed up");
        }
        
        return ResponseEntity.status(500).body("Failed to sign up");
    }


    // @GetMapping("/logout")
    // public ResponseEntity<String> signout(@RequestBody User user) {
    //     User u = service.findByUsername(user.getUsername());
    //     if (u != null) {
    //        return ResponseEntity.ok().body("Logged out");
    //     }
    //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user");        
    // }
}
