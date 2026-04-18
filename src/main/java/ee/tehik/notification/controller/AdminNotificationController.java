package ee.tehik.notification.controller;

import ee.tehik.notification.entity.Notification;
import ee.tehik.notification.service.NotificationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminNotificationController {

    private final NotificationService notificationService;

    public AdminNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Admin endpoint to create or update the notification
    @PostMapping("/notification")
    public Notification updateNotification(@RequestBody Notification notification) {
        return notificationService.saveNotification(notification);
    }
}