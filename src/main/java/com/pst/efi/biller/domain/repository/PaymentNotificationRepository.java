package com.pst.efi.biller.domain.repository;

import com.pst.efi.biller.domain.entity.PaymentNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentNotificationRepository extends JpaRepository<PaymentNotificationEntity, Long> {}
