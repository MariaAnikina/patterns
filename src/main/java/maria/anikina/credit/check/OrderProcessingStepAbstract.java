package maria.anikina.credit.check;

import maria.anikina.credit.model.Request;

public abstract class OrderProcessingStepAbstract implements OrderProcessingStep {
	private OrderProcessingStep nextOrderProcessingStep;

	public OrderProcessingStepAbstract() {
		this.nextOrderProcessingStep = null;
	}

	@Override
	public OrderProcessingStep setNextOrderProcessingStep(OrderProcessingStep orderProcessingStep) {
		nextOrderProcessingStep = orderProcessingStep;
		return nextOrderProcessingStep;
	}

	@Override
	public TypeRequestProcessing execute(TypeRequestProcessing requestProcessing, Request request) {
		if (nextOrderProcessingStep != null) {
			return nextOrderProcessingStep.execute(requestProcessing, request);
		}
		return TypeRequestProcessing.NON_EXISTENT_COMMAND;
	}
}
