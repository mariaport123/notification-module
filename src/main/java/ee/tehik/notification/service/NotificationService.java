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
    private static final String SUPPORT_CONTACT = "support@tehik.ee | +372 600 0000";

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves the current active notification based on the requested language.
     * Falls back to the latest active notification if a specific language match is
     * not found.
     * If no record exists, returns a default "Systems Operational" response.
     *
     * @param lang Optional language code (e.g., "et", "en")
     */
    public NotificationResponse getActiveNotification(String lang) {
        String language = (lang == null || lang.isEmpty()) ? "et" : lang;

        return repository.findByActiveTrueAndLanguage(language)
                .map(n -> new NotificationResponse(
                        n.getTitle(),
                        n.getContent(),
                        LocalDateTime.now(),
                        n.getSupportContact(),
                        n.isSystemOperational()))
                .orElseGet(() -> new NotificationResponse(
                        "Süsteemid on töökorras",
                        "Kõik teenused töötavad hetkel tõrgeteta.",
                        LocalDateTime.now(),
                        SUPPORT_CONTACT,
                        true));
    }

    /**
     * Saves or updates a notification record.
     * To support multi-language, it removes only the existing record for the same
     * language
     * before saving the new one.
     */
    @Transactional
    public Notification saveNotification(Notification notification) {
        String lang = notification.getLanguage() != null ? notification.getLanguage() : "et";

        notification.setLanguage(lang);
        notification.setActive(true);
        notification.setSupportContact(SUPPORT_CONTACT);

        if (notification.getServerTime() == null) {
            notification.setServerTime(LocalDateTime.now());
        }

        repository.deleteByLanguage(lang);
        notification.setId(null);

        return repository.save(notification);
    }
}