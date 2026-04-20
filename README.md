# Public Notification System

A technical assessment project consisting of a Spring Boot backend and an Angular frontend. The system allows administrators to manage public service announcements and operational status.

## Key Features
- **Dynamic Content Management:** Business users can update system-wide notifications in real-time without developer intervention.
- **Multi-language Support (Bonus):** Full support for Estonian and English content management and display.
- **System Status Indicators:** Automatic "Operational" status detection when no active maintenance notifications are present.
- **Localization:** Dynamic formatting of server-side timestamps and localized support labels (e.g., "Kasutajatugi" vs "Support").

## Bonus Implementations
### Multi-language Logic
The system uses a language-aware API. The frontend sends a `lang` parameter (`?lang=et` or `?lang=en`), and the backend retrieves the corresponding localized record. UI labels like "Server Time" and "Support" switch dynamically based on the user's selection.

### Containerization (Docker)
The project includes a multi-stage `Dockerfile`. 
- **Stage 1 (Build):** Uses JDK 25 to compile the code and run tests.
- **Stage 2 (Runtime):** Uses a slim JRE 25 image to serve the application efficiently.

### Automated Testing
Integration tests in `NotificationModuleApplicationTests` verify:
1. Application context loading.
2. Business logic for saving notifications.
3. Proper formatting of support contact details to prevent double-labeling.

## Security & Authentication
The project implements **Basic Authentication** to protect administrative endpoints.
- **Implementation:** For the convenience of this assessment, the credentials are pre-configured in the `NotificationService` on the frontend. This ensures the Admin View is fully functional upon startup without requiring manual login prompts.
- **Backend Protection:** All endpoints under `/api/admin/**` are secured using Spring Security and require the `ADMIN` role.
- **Credentials:** The system uses `admin` / `tehik2026` as the default administrative account.

## Technical Stack
- **Backend:** Java 25, Spring Boot 4, Spring Data JPA, Spring Security.
- **Build Tool:** Gradle (Kotlin DSL).
- **Database:** H2 In-Memory database for reliable data persistence during runtime.
- **Frontend:** Angular 17+, SCSS, RxJS for reactive state management.
- **Testing:** JUnit 5 and AssertJ for business logic and API integration testing.
- **Containerization:** Multi-stage Docker build targeting Java 25.

## Getting Started

### Prerequisites
- **JDK 25** (Recommended for local execution)
- **Node.js (v18+)** and **npm**
- **Docker** (Optional, for containerized execution)

### Backend
1. Navigate to the project root.
2. Ensure the Gradle wrapper has execution permissions:
   ```bash
   chmod +x gradlew
   ```
3. Run tests to verify business logic:
   ```bash
   ./gradlew test
   ``` 
4. Start the application:
   ```bash
   ./gradlew bootRun
   ```
5. The API will be available at `http://localhost:8080`.

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

## Project Structure & API Endpoints
- **Public API:** `GET /api/public/notification?lang=et` (Supports `et` and `en`) – Fetches localized content.
- **Admin API:** `POST /api/admin/notification` – Protected endpoint for updates.
- **Admin View:** Accessible via `/admin`
- **Default Credentials for Assessment:** `admin` / `tehik2026`