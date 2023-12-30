package maria.anikina.card;

import maria.anikina.client.model.Client;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class CreditCard implements Card {
	private Random random = new Random();
	public static long lastCardNumber = 2202020000000000L;
	public static long lastAccountNumber = 0L;
	private final long cardNumber;
	private int balance;
	private final Client client;
	private final int securityCode;
	private final int limit;
	private LocalDate maturityDate;

	public CreditCard(int limit, Client client) {
		this.cardNumber = generationCardNumber();
		long accountNumber = generationAccountNumber();
		this.securityCode = random.nextInt(1000);
		this.balance = limit;
		this.limit = limit;
		this.client = client;
	}

	@Override
	public void addMoneyCard(int money) {
		if (balance < limit) {
			if (maturityDate != null) {
				if (maturityDate.isBefore(LocalDate.now())) {
					client.setCreditHistoryRating(client.getCreditHistoryRating() - 1);
				} else {
					client.setCreditHistoryRating(client.getCreditHistoryRating() + 1);
				}
			}
		} else {
			maturityDate = null;
			System.out.println("Задолженности нет");
		}
		balance += money;
	}

	@Override
	public void writeMoneyOffCard(int money) {
		if (balance <= money) {
			System.out.println("Недостаточно денег на карте");
			return;
		}
		if (balance < limit) {
			balance -= money;
		} else {
			balance -= money;
			maturityDate = LocalDate.now().plus(2, ChronoUnit.MONTHS);
		}
	}

	@Override
	public long getCurrentBalance() {
		return balance;
	}

	@Override
	public String getInformationOnCard() {
		return "Кредитная карта имеет номер"
				+ String.valueOf(cardNumber).substring(0, 4) + " " + String.valueOf(cardNumber).substring(4, 8) + " "
				+ String.valueOf(cardNumber).substring(8, 12) + " " + String.valueOf(cardNumber).substring(12)
				+ ". Баланс составляет: " + balance + ". Лимит по карте = " + limit + ". Погасить задолженность нужно до "
				+ maturityDate + ". Код безопасности: " + securityCode
				+ ". Владелец карты: " + client.getFullName() + ". Владелец карты: " + client.getFullName();
	}

	private long generationCardNumber() {
		return ++lastCardNumber;
	}

	private long generationAccountNumber() {
		return ++lastAccountNumber;
	}
}
