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

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
  // Simulate receiving data instead of calling the server
  this.notification = {
    title: 'Süsteemi teavitus',
    content: 'Kõik süsteemid on töökorras. Järgmine hooldus toimub täna kell 22:00.',
    systemOperational: true,
    serverTime: new Date().toISOString(),
    supportContact: 'abi@teavitus.ee'
  };
}

  /**
   * Fetches current notification data from the backend service
   */
  private loadNotification(): void {
    this.notificationService.getNotification().subscribe({
      next: (data) => {
        this.notification = data;
      },
      error: (err) => {
        // Log error to console if the service call fails
        console.error('Failed to load notification', err);
      }
    });
  }
}