package com.integrys.backend.controllers;

import com.integrys.backend.repositories.RoleRepository;
import com.integrys.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/public")
@Transactional
public class PublicController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PublicController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    @GetMapping("")
    public ResponseEntity<Object> test() {
        return ResponseEntity.ok("Integrys Backend");

    }
}
