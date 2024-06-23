package com.pst.efi.biller.jwt;

public class SecurityConstants {

  private SecurityConstants() {}

  public static final String ADMIN = "hasRole('ADMIN')";
  public static final String USER = "hasRole('USER')";
  public static final String MODERATOR = "hasRole('MODERATOR')";
  public static final String USERNAME_TAKEN_ERROR = "Error: Username is already taken!";
  public static final String EMAIL_TAKEN_ERROR = "Error: Email is already in use!";
  public static final String ROLE_NOT_FOUND_ERROR = "Error: Role is not found.";
  public static final String USER_REGISTERED_SUCCESSFULLY = "User registered successfully!";
}
