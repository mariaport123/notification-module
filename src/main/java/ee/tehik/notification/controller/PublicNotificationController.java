package ee.tehik.notification.controller;

import ee.tehik.notification.dto.NotificationResponse;
import ee.tehik.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class PublicNotificationController {

    private final NotificationService notificationService;

    public PublicNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Get the current active notification for public view.
     * Supports optional language filtering via ?lang= query parameter.
     */
    @GetMapping("/notification")
    public NotificationResponse getNotification(@RequestParam(required = false) String lang) {
        return notificationService.getActiveNotification(lang); 
    }
}