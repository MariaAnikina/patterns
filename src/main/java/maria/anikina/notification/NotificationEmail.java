package maria.anikina.notification;

import maria.anikina.credit.model.Request;

public class NotificationEmail implements Notification {
	private Request request;

	public NotificationEmail(Request request) {
		this.request = request;
	}

	@Override
	public String sendNotification() {
		System.out.println("Отправлено клиенту " + request.getFullName()
				+ ": 1)ссылка на заявку по email; 2)SMS уведомление ");
		return "Email";
	}
}
