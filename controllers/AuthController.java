package com.integrys.backend.controllers;


import com.google.common.collect.ImmutableList;
import com.integrys.backend.entities.User;
import com.integrys.backend.entities.enums.Authority;
import com.integrys.backend.exceptions.ApiResponse;
import com.integrys.backend.repositories.RoleRepository;
import com.integrys.backend.repositories.UserRepository;
import com.integrys.backend.services.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class AuthController {
  
//  private final DefaultTokenServices tokenServices;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  @Autowired
  public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
//    this.tokenServices = tokenServices;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }


  @PostMapping("/register")
  public ResponseEntity<Object> registerUser(@Valid @RequestBody User user) {
    String username = (user.getLastname() + user.getFirstname() + "_" + System.currentTimeMillis()).replaceAll("\\s+", "");
    user.setUsername(username);
    user.setPassword(BCryptManagerUtil.passwordencoder().encode(user.getPassword()));/// J'encode le mot de passe ici
    user.setAuthorities(ImmutableList.of(roleRepository.findByName(Authority.SUPER_ADMIN)));
    userRepository.save(user);
    return ResponseEntity.accepted().body(new ApiResponse("Success registration", user));
  }



  @GetMapping(value = "/authenticated")
  public ResponseEntity<?> getAuthenticated() {
    try {
       String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      final User user = userRepository.findByUsername(username);
      if (user != null ) {
        return ResponseEntity.ok(user);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (final ClassCastException cce) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/test")
  public ResponseEntity<?> test() {
    return ResponseEntity.ok("You are logged");
  }
  

//  @DeleteMapping(value = "/logout")
//  public ResponseEntity<?> logout() {
//    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    if (auth.isAuthenticated()) {
//      final String userToken = ((OAuth2AuthenticationDetails) auth.getDetails()).getTokenValue();
//      tokenServices.revokeToken(userToken);
//      return new ResponseEntity<>(HttpStatus.OK);
//    }
//    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//  }

}
