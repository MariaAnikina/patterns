package maria.anikina;

import maria.anikina.card.Card;
import maria.anikina.card.CreditCard;
import maria.anikina.card.DebitCard;
import maria.anikina.client.model.Client;
import maria.anikina.credit.check.*;
import maria.anikina.credit.model.Request;
import maria.anikina.exception.ChecksFailedException;
import maria.anikina.notification.Notification;
import maria.anikina.notification.NotificationDecorator;
import maria.anikina.notification.NotificationEmail;
import maria.anikina.notification.NotificationSMS;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class FunctionalityCheckTest {
	private Client client;
	private Card card1;
	private Card card2;

	@Before
	public void createEntities() {
		client = new Client("Иванов Иван Иванович", 2222222222L,
				"89969114878", "n@mail.ru");
		card1 = new DebitCard(0, client);
		card2 = new CreditCard(2000, client);
	}

	@Test
	public void checkingOperationOfCreditAndDebitCards() {
		System.out.println(card1.getInformationOnCard());
		System.out.println(card2.getInformationOnCard());

		card1.addMoneyCard(100);
		assertEquals(card1.getCurrentBalance(), 100);

		card2.writeMoneyOffCard(200);
		assertEquals(card2.getCurrentBalance(), 1800);

		System.out.println(card1.getInformationOnCard());
		System.out.println(card2.getInformationOnCard());

		card2.addMoneyCard(300);
		System.out.println(card2.getInformationOnCard());
		System.out.println(client);
	}

	@Test
	public void creditApplicationVerification_whenCreditHistoryLessThanOne_thenReturnChecksFailedException() {
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "n@mail.ru");

		OrderProcessingStep validationStep = new ValidationStep();
		OrderProcessingStep complianceStep = new ComplianceStep();
		OrderProcessingStep solutionStep = new SolutionStep();

		validationStep.setNextOrderProcessingStep(complianceStep);
		complianceStep.setNextOrderProcessingStep(solutionStep);

		assertThrows(ChecksFailedException.class, () -> validationStep.execute(TypeRequestProcessing.SOLUTION, request));
	}

	@Test
	public void creditApplicationVerification_whenClientTerrorist_thenReturnChecksFailedException() {
		client.setFullName("Террорист");
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "n@mail.ru");

		OrderProcessingStep validationStep = new ValidationStep();
		OrderProcessingStep complianceStep = new ComplianceStep();
		OrderProcessingStep solutionStep = new SolutionStep();

		validationStep.setNextOrderProcessingStep(complianceStep);
		complianceStep.setNextOrderProcessingStep(solutionStep);

		assertThrows(ChecksFailedException.class, () -> validationStep.execute(TypeRequestProcessing.SOLUTION, request));
	}

	@Test
	public void creditApplicationVerification_whenBadRequestIncorrectEmail_thenReturnChecksFailedException() {
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "mail.ru");

		OrderProcessingStep validationStep = new ValidationStep();
		OrderProcessingStep complianceStep = new ComplianceStep();
		OrderProcessingStep solutionStep = new SolutionStep();

		validationStep.setNextOrderProcessingStep(complianceStep);
		complianceStep.setNextOrderProcessingStep(solutionStep);

		assertThrows(ChecksFailedException.class, () -> validationStep.execute(TypeRequestProcessing.SOLUTION, request));
	}

	@Test
	public void creditApplicationVerification_whenAllChecksPassed_thenReturnRequestApproved() {
		card2.writeMoneyOffCard(200);
		card2.addMoneyCard(300);
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "m@mail.ru");

		OrderProcessingStep validationStep = new ValidationStep();
		OrderProcessingStep complianceStep = new ComplianceStep();
		OrderProcessingStep solutionStep = new SolutionStep();

		validationStep.setNextOrderProcessingStep(complianceStep);
		complianceStep.setNextOrderProcessingStep(solutionStep);

		assertEquals(TypeRequestProcessing.REQUEST_APPROVED, validationStep.execute(TypeRequestProcessing.SOLUTION, request));
	}

	@Test
	public void verificationNotificationSending_whenSMSSent_thenReturnSMS() {
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "m@mail.ru");
		Notification notification = new NotificationSMS(request);

		assertEquals("SMS", notification.sendNotification());
	}

	@Test
	public void verificationNotificationSending_whenEmailSent_thenReturnEmail() {
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "m@mail.ru");
		Notification notification = new NotificationEmail(request);

		assertEquals("Email", notification.sendNotification());
	}

	@Test
	public void verificationNotificationSending_whenSMSAndEmailSent_thenReturnEmailAndSMS() {
		Request request = new Request(client, 140000, 0.1, 500000,
				12, "89969114878", "m@mail.ru");
		Notification notification = new NotificationSMS(request);
		Notification notification2 = new NotificationEmail(request);
		NotificationDecorator notificationDecorator = new NotificationDecorator(notification, notification2);

		assertEquals("SMS и Email", notificationDecorator.sendNotification());
	}
}
