import { Routes } from '@angular/router';
import { PublicAnnouncementComponent } from './components/public-announcement/public-announcement';
import { AdminComponent } from './components/admin/admin.component';

export const routes: Routes = [
  // Public view: Displays active notifications to all users
  { path: '', component: PublicAnnouncementComponent },
  
  // Admin view: Interface for managing notification content
  { path: 'admin', component: AdminComponent },
  
  // Fallback: Redirect any unknown paths to the public view
  { path: '**', redirectTo: '' }
];