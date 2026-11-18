package com.sheyie.notificationservice.dto;

import com.sheyie.notificationservice.enums.Channel;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NotificationRequest(
        @NotBlank(message = "User ID is required")
        String userId,
        @NotNull(message = "Channel is required")
        Channel channel,
        @NotBlank(message = "Subject is required")
        @Size(max = 100, message = "Subject must be at most 100 charcters")
        String subject,
        @NotBlank(message = "Message is required")
        String message
) {
}
