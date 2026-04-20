import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NotificationService } from '../../services/notification.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})
export class AdminComponent implements OnInit {
  // We initialize with empty strings to avoid 'null' type issues
  notification = {
    title: '',
    content: '',
    active: true,
    language: 'et',
    systemOperational: false // Default on Maintenance
  };

  message = '';
  isError: boolean = false;

  constructor(private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.loadAdminData();
  }

  /**
   * Fetches notification data from the server based on the selected language.
   */
  loadAdminData(): void {
    this.notificationService.getNotification(this.notification.language).subscribe({
      next: (data: any) => { // Added explicit 'any' type to fix "implicitly has any type" error
        if (data) {
          // We use logical OR to ensure we never assign null to a string field
          this.notification.title = data.title || '';
          this.notification.content = data.content || '';
        }
      },
      error: (err: any) => console.error('Failed to load existing notification:', err)
    });
  }

  /**
   * Sends the updated notification data to the backend.
   */
  save(): void {
    /** * If you get an error that 'saveNotification' does not exist, 
     * check your notification.service.ts file. 
     * If the method there is named 'updateNotification', change this call to that.
     */
    this.notificationService.updateNotification(this.notification).subscribe({
      next: (response: any) => { // Added explicit 'any' type
        this.message = 'Notification updated successfully!';
        this.isError = false;
        console.log('Server response:', response);
      },
      error: (err: any) => { // Added explicit 'any' type
        this.message = 'Error updating notification. Check server logs.';
        this.isError = true;
        console.error('Save failed:', err);
      }
    });
  }
}