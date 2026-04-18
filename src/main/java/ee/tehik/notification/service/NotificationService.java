package ee.tehik.notification.service;

import ee.tehik.notification.dto.NotificationResponse;
import ee.tehik.notification.entity.Notification;
import ee.tehik.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository repository;
    
    // Fixed support contact 
    private static final String SUPPORT_CONTACT = "Customer Support: support@tehik.ee | +372 600 0000";

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    // This method decides what the Public API returns
    public NotificationResponse getActiveNotification() {
        return repository.findFirstByActiveTrue()
            .map(n -> new NotificationResponse(
                n.getTitle(),
                n.getContent(),
                LocalDateTime.now(),
                SUPPORT_CONTACT,
                false // systemOperational is false because there's an active alert/info
            ))
            .orElseGet(() -> new NotificationResponse(
                null,
                null,
                LocalDateTime.now(),
                SUPPORT_CONTACT,
                true // No active notification means systems are operational
            ));
    }

    // Method for Admin to save a new or updated notification
    public Notification saveNotification(Notification notification) {
        return repository.save(notification);
    }
}