package com.pst.efi.biller.service.notification;

import com.pst.efi.biller.dto.notification.PaymentNotificationRequest;
import com.pst.efi.biller.dto.notification.PaymentNotificationResponse;

public interface NotificationService {
    public PaymentNotificationResponse receiveNotification(PaymentNotificationRequest request);
}
