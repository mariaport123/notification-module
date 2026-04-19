import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NotificationResponse } from '../models/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private apiUrl = 'http://localhost:8080/api/public/status';

  constructor(private http: HttpClient) { }

  // Fetches the current system status or notification
  getNotification(): Observable<NotificationResponse> {
    return this.http.get<NotificationResponse>(this.apiUrl);
  }
}