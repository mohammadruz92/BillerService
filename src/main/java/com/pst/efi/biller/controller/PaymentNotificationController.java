package com.pst.efi.biller.controller;

import com.pst.efi.biller.dto.notification.PaymentNotificationRequest;
import com.pst.efi.biller.dto.notification.PaymentNotificationResponse;
import com.pst.efi.biller.service.notification.NotificationService;
import com.pst.efi.biller.jwt.SecurityConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class PaymentNotificationController {

  private final NotificationService service;

  public PaymentNotificationController(NotificationService service) {
    this.service = service;
  }

  @PostMapping(
      produces = "application/json;charset=UTF-8",
      consumes = "application/json;charset=UTF-8")
  @PreAuthorize(SecurityConstants.MODERATOR)
  public ResponseEntity<PaymentNotificationResponse> receive(
      @RequestBody PaymentNotificationRequest request) {
    service.receiveNotification(request);
    return ResponseEntity.ok(service.receiveNotification(request));
  }
}
