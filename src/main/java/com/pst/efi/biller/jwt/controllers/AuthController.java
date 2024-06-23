package com.pst.efi.biller.jwt.controllers;

import com.pst.efi.biller.jwt.SecurityConstants;
import com.pst.efi.biller.jwt.payload.request.LoginRequest;
import com.pst.efi.biller.jwt.payload.request.SignupRequest;
import com.pst.efi.biller.jwt.security.services.AuthinticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthinticationServiceImpl service;

  public AuthController(AuthinticationServiceImpl service) {
    this.service = service;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    return service.authenticateUser(loginRequest);
  }

  @PostMapping("/signup")
  @PreAuthorize(SecurityConstants.ADMIN)
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return service.registerUser(signUpRequest);
  }
}
