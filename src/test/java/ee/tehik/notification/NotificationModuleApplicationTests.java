package ee.tehik.notification;

import ee.tehik.notification.entity.Notification;
import ee.tehik.notification.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationModuleApplicationTests {

    @Autowired
    private NotificationService notificationService;

    @Test
    void contextLoads() {
        // Basic check to ensure the application context starts correctly
    }

    @Test
    void testSaveNotificationLogic() {
        // Create a new notification object
        Notification notification = new Notification();
        notification.setTitle("Test Title");
        notification.setContent("Test Content");
        notification.setLanguage("en");
        notification.setSystemOperational(true);

        // Save using the service
        Notification saved = notificationService.saveNotification(notification);

        // Verify business logic (ID generation, language persistence, etc.)
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getLanguage()).isEqualTo("en");
        assertThat(saved.isSystemOperational()).isTrue();
        
        // Verify that our fixed support contact was applied
        assertThat(saved.getSupportContact()).contains("support@tehik.ee");
    }
}