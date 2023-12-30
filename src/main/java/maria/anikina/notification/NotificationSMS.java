package maria.anikina.notification;

import maria.anikina.credit.model.Request;

public class NotificationSMS implements Notification {
	private final Request request;

	public NotificationSMS(Request request) {
		this.request = request;
	}

	@Override
	public String sendNotification() {
		System.out.println("Отправлено: SMS с заявкой клиенту " + request.getFullName());
		return "SMS";
	}
}
