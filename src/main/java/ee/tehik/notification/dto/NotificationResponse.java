package ee.tehik.notification.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for public API response.
 * Using a record for a clean, immutable data structure.
 */
public record NotificationResponse(
    String title,
    String content,
    LocalDateTime serverTime,
    String supportContact,
    boolean systemOperational
) {}