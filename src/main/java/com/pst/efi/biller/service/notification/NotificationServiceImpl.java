package com.pst.efi.biller.service.notification;

import com.pst.efi.biller.domain.repository.PaymentNotificationRepository;
import com.pst.efi.biller.dto.notification.PaymentNotificationRequest;
import com.pst.efi.biller.dto.notification.PaymentNotificationResponse;
import com.pst.efi.biller.mapper.PaymentNotificationMapper;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    private final PaymentNotificationRepository repository;

    public NotificationServiceImpl(PaymentNotificationRepository repository) {
        this.repository = repository;
    }


    @Override
    public PaymentNotificationResponse receiveNotification(PaymentNotificationRequest request) {
        repository.save(PaymentNotificationMapper.toEntity(request));
        return PaymentNotificationMapper.toResponse(request);
    }
}
