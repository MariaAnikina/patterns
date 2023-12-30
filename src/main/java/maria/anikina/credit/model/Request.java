package maria.anikina.credit.model;

import maria.anikina.client.model.Client;
import maria.anikina.notification.Notification;

public class Request {
	private Client client;
	private String fullName;
	private Long passportSeriesAndNumber;
	private String phoneNumber;
	private String email;
	private Integer monthlyIncome;
	private Integer creditHistory;
	private Double interestRate;
	private Integer sumCredit;
	private Integer term;
	private Double monthlyPayment;

	public Request(Client client, Integer monthlyIncome, Double interestRate, Integer sumCredit, Integer term,
	               String phoneNumber, String email) {
		this.client = client;
		fullName = client.getFullName();
		passportSeriesAndNumber = client.getPassportSeriesAndNumber();
		creditHistory = client.getCreditHistoryRating();
		this.monthlyIncome = monthlyIncome;
		this.interestRate = interestRate;
		this.sumCredit = sumCredit;
		this.term = term;
		this.phoneNumber = phoneNumber;
		this.email = email;
		monthlyPayment = setMonthlyPayment();
	}

	private Double setMonthlyPayment() {
		return sumCredit * interestRate / 12 * Math.pow(1 + interestRate / 12, term) / (Math.pow(1 + interestRate / 12, term) - 1);
	}

	public String getFullName() {
		return fullName;
	}

	public Long getPassportSeriesAndNumber() {
		return passportSeriesAndNumber;
	}

	public Integer getMonthlyIncome() {
		return monthlyIncome;
	}

	public Integer getCreditHistory() {
		return creditHistory;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public Integer getSumCredit() {
		return sumCredit;
	}

	public Integer getTerm() {
		return term;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "Request{" +
				"client=" + client +
				", fullName='" + fullName + '\'' +
				", passportSeriesAndNumber=" + passportSeriesAndNumber +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				", monthlyIncome=" + monthlyIncome +
				", creditHistory=" + creditHistory +
				", interestRate=" + interestRate +
				", sumCredit=" + sumCredit +
				", term=" + term +
				", monthlyPayment=" + monthlyPayment +
				'}';
	}
}
