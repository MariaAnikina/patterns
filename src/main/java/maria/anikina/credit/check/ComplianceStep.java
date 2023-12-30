package maria.anikina.credit.check;

import maria.anikina.credit.model.Request;
import maria.anikina.exception.ChecksFailedException;

public class ComplianceStep extends OrderProcessingStepAbstract {
	@Override
	public TypeRequestProcessing execute(TypeRequestProcessing requestProcessing, Request request) {
		if (requestProcessing.equals(TypeRequestProcessing.COMPLIANCE)) {
			if (request.getFullName().equals("Террорист")) {
				throw new ChecksFailedException("Небезопасный клиент");
			}
			System.out.println("Шаг 1: валидация прошла успешно, Шаг 2: клиент не террорист");
		}
		return super.execute(requestProcessing, request);
	}
}
