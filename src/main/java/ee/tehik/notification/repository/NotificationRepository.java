package ee.tehik.notification.repository;

import ee.tehik.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
    // Finds the notification that is currently active. 
    // We assume only one is active at a time for simplicity.
    Optional<Notification> findFirstByActiveTrue();
}