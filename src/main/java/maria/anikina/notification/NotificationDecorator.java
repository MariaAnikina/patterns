package maria.anikina.notification;

public class NotificationDecorator implements Notification {
	private final Notification notification;
	private final Notification notification2;

	public NotificationDecorator(Notification notification, Notification notification2) {
		this.notification = notification;
		this.notification2 = notification2;
	}

	@Override
	public String sendNotification() {
		return notification.sendNotification() + " и " + notification2.sendNotification();
	}
}
