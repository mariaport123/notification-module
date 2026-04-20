# Public Notification System

A technical assessment project consisting of a Spring Boot backend and an Angular frontend. The system allows administrators to manage public service announcements and operational status.

## Features
- **Public View:** Displays the current system status and active announcements.
- **Admin View:** A secure dashboard to update the notification title, content, and active status.
- **Dynamic Updates:** Real-time feedback when the operational status changes.

## Security & Authentication
The project implements **Basic Authentication** to protect administrative endpoints.
- **Implementation:** For the convenience of this assessment, the credentials are pre-configured in the `NotificationService` on the frontend. This ensures the Admin View is fully functional upon startup without requiring manual login prompts.
- **Backend Protection:** All endpoints under `/api/admin/**` are secured using Spring Security and require the `ADMIN` role.
- **Credentials:** The system uses `admin` / `tehik2026` as the default administrative account.

## Technical Stack
- **Backend:** Java 21, Spring Boot 3, Spring Security, H2 Database (In-Memory).
- **Frontend:** Angular, SCSS, RxJS.

## Getting Started

### Prerequisites
- JDK 21 or higher
- Node.js (v18+) and npm

### Backend
1. Navigate to the project root.
2. Run the application:
   ```bash
   ./gradlew bootRun
   ``` 
3. The API will be available at `http://localhost:8080`.

4. H2 Console (for debugging):`http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:notificationdb`).

### Frontend
1. Navigate to the frontend directory.
2. Install dependencies:
```bash
npm install
```
3. Start the development server:
```bash
npm start
```
4. Access the application at `http://localhost:4200`.

### Project Structure
- `/api/public/notification`: Publicly accessible endpoint for fetching system status and active notifications.
- `/api/admin/notification`: Protected endpoint requiring Basic Auth for creating or updating notifications.