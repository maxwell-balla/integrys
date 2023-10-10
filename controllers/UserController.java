package com.integrys.backend.controllers;

import com.integrys.backend.entities.Setting;
import com.integrys.backend.entities.User;
import com.integrys.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @GetMapping("/")
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable(name = "id") String id) {
//        if (this.userRepository.existsById(id)) {
//            user.setId(id);
//            return ResponseEntity.ok().body(this.userRepository.save(user));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
