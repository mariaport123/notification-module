package ee.tehik.notification.controller;

import ee.tehik.notification.dto.NotificationResponse;
import ee.tehik.notification.service.NotificationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200") // Required for Angular to talk to Spring
public class PublicNotificationController {

    private final NotificationService notificationService;

    public PublicNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Public endpoint to check system status
    @GetMapping("/status")
    public NotificationResponse getStatus() {
        return notificationService.getActiveNotification();
    }
}