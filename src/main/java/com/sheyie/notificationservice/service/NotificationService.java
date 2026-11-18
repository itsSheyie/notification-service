package com.sheyie.notificationservice.service;

import com.sheyie.notificationservice.dto.NotificationRequest;
import com.sheyie.notificationservice.dto.NotificationResponse;
import com.sheyie.notificationservice.entity.Notification;
import com.sheyie.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;


    public NotificationResponse createNotification(NotificationRequest request, String idempotencyKey){

        if(idempotencyKey != null){
            var existing = notificationRepository.findByIdempotencyKey(idempotencyKey);
            if (existing.isPresent()){
                return NotificationResponse.mapToResponse(existing.get());
            }
        }
        Notification notification = Notification.builder()
                .userId(request.userId())
                .channel(request.channel())
                .subject(request.subject())
                .message(request.message())
                .idempotencyKey(idempotencyKey)
                .build();

        try{
           Notification saved = notificationRepository.save(notification);
           return NotificationResponse.mapToResponse(saved);
        }catch (DataIntegrityViolationException e){
            if(idempotencyKey == null) throw e;
            return notificationRepository.findByIdempotencyKey(idempotencyKey)
                    .map(NotificationResponse::mapToResponse)
                    .orElseThrow(()-> e);
        }


    }

}
