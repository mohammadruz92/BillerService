package com.pst.efi.biller.jwt.security.services;

import com.pst.efi.biller.jwt.SecurityConstants;
import com.pst.efi.biller.jwt.models.ERole;
import com.pst.efi.biller.jwt.models.Role;
import com.pst.efi.biller.jwt.models.User;
import com.pst.efi.biller.jwt.payload.request.LoginRequest;
import com.pst.efi.biller.jwt.payload.request.SignupRequest;
import com.pst.efi.biller.jwt.payload.response.JwtResponse;
import com.pst.efi.biller.jwt.payload.response.MessageResponse;
import com.pst.efi.biller.jwt.repository.RoleRepository;
import com.pst.efi.biller.jwt.repository.UserRepository;
import com.pst.efi.biller.jwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthinticationServiceImpl {

  @Autowired AuthenticationManager authenticationManager;
  @Autowired UserRepository userRepository;

  @Autowired RoleRepository roleRepository;

  @Autowired PasswordEncoder encoder;

  @Autowired JwtUtils jwtUtils;

  public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

    return ResponseEntity.ok(
        new JwtResponse(
            jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
  }

  public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {

    if (Boolean.TRUE.equals(userRepository.existsByUsername(signUpRequest.getUsername()))) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse(SecurityConstants.USERNAME_TAKEN_ERROR));
    }

    if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
      return ResponseEntity.badRequest()
          .body(new MessageResponse(SecurityConstants.EMAIL_TAKEN_ERROR));
    }

    // Create new user's account
    User user =
        new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    Role defaultRole =
        roleRepository
            .findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException(SecurityConstants.ROLE_NOT_FOUND_ERROR));

    if (strRoles == null || strRoles.isEmpty()) {
      roles.add(defaultRole);
    } else {
      strRoles.forEach(
          role -> {
            Role foundRole = getRoleByName(ERole.valueOf(role));
            roles.add(foundRole != null ? foundRole : defaultRole);
          });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse(SecurityConstants.USER_REGISTERED_SUCCESSFULLY));
  }

  private Role getRoleByName(ERole role) {
    return roleRepository.findByName(role).orElse(null);
  }
}
