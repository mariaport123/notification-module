package ee.tehik.notification.service;

import ee.tehik.notification.dto.NotificationResponse;
import ee.tehik.notification.entity.Notification;
import ee.tehik.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    // Support contact details used across the service
    private static final String SUPPORT_CONTACT = "Customer Support: support@tehik.ee | +372 600 0000";

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves the current active notification from the database.
     * If no active notification is found, returns a default "systems operational"
     * status.
     */
    public NotificationResponse getActiveNotification() {
        return repository.findFirstByActiveTrueOrderByIdDesc()
                .map(n -> new NotificationResponse(
                        n.getTitle(),
                        n.getContent(),
                        LocalDateTime.now(),
                        SUPPORT_CONTACT,
                        false))
                .orElseGet(() -> new NotificationResponse(
                        "Systems Operational",
                        "All services are running smoothly. No active maintenance at the moment.",
                        LocalDateTime.now(),
                        SUPPORT_CONTACT,
                        true));
    }

    /**
     * Saves or updates a notification record in the database.
     */
    @Transactional // Lisa see annotatsioon meetodi kohale
    public Notification saveNotification(Notification notification) {
        repository.deleteAll(); 
        notification.setId(null); 
        return repository.save(notification);
    }
}