package Entity;

import java.time.LocalDateTime;

public class Credit {
	private String Id;
	private int CreditId;
	private float CreditAmount;
	private float CreditTotalPays;
	private int DayPaymenPerMonth;
	private LocalDateTime CreditDate;

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public int getCreditIdid() {
		return CreditId;
	}

	public void setCreditIdid(int creditIdid) {
		CreditId = creditIdid;
	}

	public float getCreditAmount() {
		return CreditAmount;
	}

	public void setCreditAmount(float creditAmount) {
		CreditAmount = creditAmount;
	}

	public float getCreditTotalPays() {
		return CreditTotalPays;
	}

	public void setCreditTotalPays(float creditTotalPays) {
		CreditTotalPays = creditTotalPays;
	}

	public int getDayPaymenPerMonth() {
		return DayPaymenPerMonth;
	}

	public LocalDateTime getCreditDate() {
		return CreditDate;
	}

	public void setCreditDate(LocalDateTime creditDate) {
		CreditDate = creditDate;
	}

	public Credit( int creditId,String Id, float creditAmount, float creditTotalPays, int date, LocalDateTime creditDate) {
		super();
		this.Id = Id;
		CreditId = creditId;
		CreditAmount = creditAmount;
		CreditTotalPays = creditTotalPays;
		DayPaymenPerMonth = date;
		CreditDate = creditDate;
	}

	public String toString() {
		return String.valueOf(CreditId);
	}
}
