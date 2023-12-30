package maria.anikina.credit.check;

import maria.anikina.credit.model.Request;
import maria.anikina.notification.Notification;

public interface OrderProcessingStep {
	OrderProcessingStep setNextOrderProcessingStep(OrderProcessingStep orderProcessingStep);
	TypeRequestProcessing execute(TypeRequestProcessing requestProcessing, Request request);
}
