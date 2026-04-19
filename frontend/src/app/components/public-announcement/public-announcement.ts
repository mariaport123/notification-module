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

  constructor(private notificationService: NotificationService) {}

  ngOnInit(): void {
    /**
     * Fetch the notification data from the backend service.
     */
    this.notificationService.getNotification().subscribe({
      next: (data) => {
        this.notification = data;
      },
      error: (err) => {
        // Professional error logging in the console
        console.error('Failed to fetch system notification:', err);
      }
    });
  }
}