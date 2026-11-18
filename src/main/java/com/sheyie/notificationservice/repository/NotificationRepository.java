package com.sheyie.notificationservice.repository;

import com.sheyie.notificationservice.entity.Notification;

import com.sheyie.notificationservice.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByUserIdOrderByCreatedAtDesc(String userId);
    List<Notification> findByStatus(Status status);

    Optional<Notification> findByIdempotencyKey(String idempotencyKey);
}
