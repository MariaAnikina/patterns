package maria.anikina.credit.check;

import maria.anikina.credit.model.Request;
import maria.anikina.exception.ChecksFailedException;
import maria.anikina.notification.Notification;

public class SolutionStep extends OrderProcessingStepAbstract {
	@Override
	public TypeRequestProcessing execute(TypeRequestProcessing requestProcessing, Request request) {
		if (requestProcessing.equals(TypeRequestProcessing.SOLUTION)) {
			if (!approveCredit(request)) {
				throw new ChecksFailedException("Банк не одобрил кредит по заявке: " + request);
			}
			System.out.println("Шаг 1: валидация прошла успешно, Шаг 2: клиент не террорист, Шаг 3: заявка одобрена");
			return TypeRequestProcessing.REQUEST_APPROVED;
		}
		return super.execute(requestProcessing, request);
	}

	public void sendNotification(Notification notification){
		notification.sendNotification();
	}

	private boolean approveCredit(Request request) {
		if (request.getMonthlyIncome() - request.getMonthlyPayment() < 20000) {
			return false;
		}
		if (request.getCreditHistory() < 1 || request.getInterestRate() < 0.09) {
			return false;
		}
		if (request.getSumCredit() > 1_000_000 && request.getMonthlyIncome() < 50_000) {
			return false;
		}
		return true;
	}
}
