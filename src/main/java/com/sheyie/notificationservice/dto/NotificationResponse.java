package com.sheyie.notificationservice.dto;

import com.sheyie.notificationservice.entity.Notification;
import com.sheyie.notificationservice.enums.Channel;
import com.sheyie.notificationservice.enums.Status;

import java.util.UUID;

public record NotificationResponse(
        UUID id,
        Status status,
        Channel channel,
        String subject,
        String message
) {
    public static NotificationResponse mapToResponse(Notification notification){
        return new NotificationResponse(notification.getId(),
                notification.getStatus(),
                notification.getChannel(),
                notification.getSubject(),
                notification.getMessage());
    }
}
