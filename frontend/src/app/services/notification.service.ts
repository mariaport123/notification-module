import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NotificationResponse } from '../models/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private readonly publicUrl = 'http://localhost:8080/api/public/status';
  private readonly adminUrl = 'http://localhost:8080/api/admin/notification';

  constructor(private http: HttpClient) { }

  /**
   * Fetches the current system status or active notification for the public view.
   * @returns An Observable of NotificationResponse containing the status details.
   */
  getNotification(): Observable<NotificationResponse> {
    return this.http.get<NotificationResponse>(this.publicUrl);
  }

  /**
   * Updates the system notification settings. 
   * Access is restricted to administrators via Basic Authentication.
   * @param notification The notification object to be saved.
   * @returns An Observable of the saved notification.
   */
  updateNotification(notification: any): Observable<any> {
    // Construct headers with Basic Authentication credentials
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:tehik2026'),
      'Content-Type': 'application/json'
    });

    return this.http.post(this.adminUrl, notification, { headers });
  }
}