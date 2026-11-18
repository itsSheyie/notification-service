package com.sheyie.notificationservice.controller;

import com.sheyie.notificationservice.dto.NotificationRequest;
import com.sheyie.notificationservice.dto.NotificationResponse;
import com.sheyie.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<NotificationResponse> createNotification(@Valid @RequestBody NotificationRequest request,
                                                                   @RequestHeader(value = "Idempotency-Key", required = false)String idempotencyKey){
        return ResponseEntity.accepted()
                .body(notificationService.createNotification(request, idempotencyKey));
    }
}
