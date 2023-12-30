package maria.anikina.client.model;

public class Client {
	private static long lastId = 0L;
	private long id;
	private String fullName;
	private long passportSeriesAndNumber;
	private int creditHistoryRating;
	private String phoneNumber;
	private String email;

	public Client(String fullName, long passportSeriesAndNumber, String phoneNumber, String email) {
		id = generationId();
		this.fullName = fullName;
		this.passportSeriesAndNumber = passportSeriesAndNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		creditHistoryRating = 0;
	}

	private long generationId() {
		return ++lastId;
	}

	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				", passportSeriesAndNumber=" + passportSeriesAndNumber +
				", creditHistoryRating=" + creditHistoryRating +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public int getCreditHistoryRating() {
		return creditHistoryRating;
	}

	public void setCreditHistoryRating(int creditHistoryRating) {
		this.creditHistoryRating = creditHistoryRating;
	}

	public long getPassportSeriesAndNumber() {
		return passportSeriesAndNumber;
	}

	public void setPassportSeriesAndNumber(long passportSeriesAndNumber) {
		this.passportSeriesAndNumber = passportSeriesAndNumber;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
