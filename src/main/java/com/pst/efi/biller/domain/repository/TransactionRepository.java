package com.pst.efi.biller.domain.repository;

import com.pst.efi.biller.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

  @Transactional
  @Modifying
  @Query("update transaction t set t.response = ?1 where t.id = ?2")
  int updateById(String response, Long id);
}
