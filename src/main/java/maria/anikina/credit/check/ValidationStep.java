package maria.anikina.credit.check;

import maria.anikina.credit.model.Request;
import maria.anikina.exception.ChecksFailedException;

public class ValidationStep extends OrderProcessingStepAbstract {

	@Override
	public TypeRequestProcessing execute(TypeRequestProcessing requestProcessing, Request request) {
		if (requestProcessing.equals(TypeRequestProcessing.CHECK)) {
			validate(request);
			System.out.println("Шаг 1: валидация прошла успешно");
		}
		return super.execute(requestProcessing, request);
	}

	private void validate(Request request) {
		if (request.getFullName() == null || request.getFullName().isEmpty()) {
			throw new ChecksFailedException("ФИО заполнено некорректно");
		}
		if (request.getPassportSeriesAndNumber() == null || request.getPassportSeriesAndNumber().toString().length() != 10) {
			throw new ChecksFailedException("Паспортные данные заполнены некорректно");
		}
		if (request.getMonthlyIncome() == null) {
			throw new ChecksFailedException("Не указан доход");
		}
		if (request.getInterestRate() == null || request.getInterestRate() < 0 || request.getInterestRate() > 1) {
			throw new ChecksFailedException("Поле ставки не заполнено или его значение не пренадлежит промежутко от 0 до 1");
		}
		if (request.getSumCredit() == null || request.getSumCredit() < 0) {
			throw new ChecksFailedException("Некорректно заполнена желаемая сумма кредита");
		}
		if (request.getTerm() == null || request.getTerm() < 0) {
			throw new ChecksFailedException("Некорректно заполнен срок, на который планируется взять кредит");
		}
		if (request.getPhoneNumber().length() != 11 || request.getPhoneNumber().length() != 12) {
			throw new ChecksFailedException("Некорректно заполнен номер телефона");
		}
		if (request.getPhoneNumber().charAt(0) != '8' || !request.getPhoneNumber().startsWith("+7")) {
			throw new ChecksFailedException("Номер телефона должен начинаться с +7 или 8");
		}
		if (!request.getEmail().contains("@")) {
			throw new ChecksFailedException("Email записан некорректно");
		}
	}
}

