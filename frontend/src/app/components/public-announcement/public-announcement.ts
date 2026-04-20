import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from '../../services/notification.service';
import { NotificationResponse } from '../../models/notification.model';

@Component({
  selector: 'app-public-announcement',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './public-announcement.html',
  styleUrl: './public-announcement.scss'
})
export class PublicAnnouncementComponent implements OnInit {
  notification: NotificationResponse | null = null;
  
  // Track the currently selected language for UI state
  currentLang: string = 'et';

  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    // Initial fetch using the default language
    this.loadNotification('et');
  }

  /**
   * Fetches the notification data from the backend service based on the selected language.
   * @param lang The language code to fetch (e.g., 'et', 'en')
   */
  loadNotification(lang: string): void {
    this.currentLang = lang;
    this.notificationService.getNotification(lang).subscribe({
      next: (data) => {
        this.notification = data;
      },
      error: (err) => {
        // Professional error logging in the console
        console.error(`Failed to fetch system notification for language: ${lang}`, err);
      }
    });
  }
}