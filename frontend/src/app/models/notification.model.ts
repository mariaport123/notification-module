export interface NotificationResponse {
  title: string | null;
  content: string | null;
  serverTime: string;
  supportContact: string;
  systemOperational: boolean;
}