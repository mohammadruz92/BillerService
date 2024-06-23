package com.pst.efi.biller.jwt.repository;


import java.util.Optional;

import com.pst.efi.biller.jwt.models.ERole;
import com.pst.efi.biller.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
