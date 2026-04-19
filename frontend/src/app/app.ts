import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PublicAnnouncementComponent } from './components/public-announcement/public-announcement';

@Component({
  selector: 'app-root',
  standalone: true, 
  imports: [RouterOutlet, PublicAnnouncementComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('frontend');
}