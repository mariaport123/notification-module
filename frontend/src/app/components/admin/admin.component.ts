import { Component } from '@angular/core';
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
export class AdminComponent {
  notification = {
    title: '',
    content: '',
    active: false
  };

  message = '';
  isError: boolean = false; // This tracks if the current message is an error

  constructor(private notificationService: NotificationService) {}

  /**
   * Sends the updated notification data to the backend.
   */
  save(): void {
    this.notificationService.updateNotification(this.notification).subscribe({
      next: (response) => {
        // Success case: update message and clear error state
        this.message = 'Notification updated successfully!';
        this.isError = false; 
        console.log('Server response:', response);
      },
      error: (err) => {
        // Error case: show error message and set error state to true
        this.message = 'Error updating notification. Check credentials.';
        this.isError = true;
        console.error('Save failed:', err);
      }
    });
  }
}