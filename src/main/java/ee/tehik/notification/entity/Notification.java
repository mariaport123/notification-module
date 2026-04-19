package ee.tehik.notification.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The main title for the notification message
    private String title;

    // Detailed content about the system status or maintenance
    private String content;

    // Indicates if this specific notification should be shown to users
    private boolean active;

    private boolean systemOperational;
    
    private LocalDateTime serverTime = LocalDateTime.now();
    
    private String supportContact;
}