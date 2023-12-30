package maria.anikina.card;

public interface Card {

	void addMoneyCard(int money);

	void writeMoneyOffCard(int money);

	long getCurrentBalance();

	String getInformationOnCard();
}
