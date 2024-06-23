package com.pst.efi.biller.jwt.models;

public enum ERole {
  ROLE_USER("User"),
  ROLE_MODERATOR("Moderator"),
  ROLE_ADMIN("Administrator");

  private final String displayName;

  ERole(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
