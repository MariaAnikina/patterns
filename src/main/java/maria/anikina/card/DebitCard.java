package maria.anikina.card;

import maria.anikina.client.model.Client;

import java.util.Random;

public class DebitCard implements Card {

	private Random random = new Random();
	public static long lastCardNumber = 2202021000000000L;
	private long cardNumber;
	private Client client;
	private int securityCode;
	private int balance;


	public DebitCard(int balance, Client client) {
		this.cardNumber = generationNumber();
		this.balance = balance;
		this.client = client;
		securityCode = random.nextInt(999);
	}

	@Override
	public void addMoneyCard(int money) {
		balance += money;
	}

	@Override
	public void writeMoneyOffCard(int money) {
		if (balance >= money) {
			balance -= money;
		}
	}

	@Override
	public long getCurrentBalance() {
		return balance;
	}

	@Override
	public String getInformationOnCard() {
		return "Дебитовая карта имеет номер"
				+ String.valueOf(cardNumber).substring(0,4) + " " + String.valueOf(cardNumber).substring(4,8) + " "
				+ String.valueOf(cardNumber).substring(8,12) + " " + String.valueOf(cardNumber).substring(12)
				+ ". Баланс составляет: " + balance;
	}

	private long generationNumber() {
		return ++lastCardNumber;
	}
}
