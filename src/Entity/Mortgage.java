package Entity;

import java.time.LocalDateTime;

public class Mortgage {
	private String id;
	private String MortgageId;
	private float MortgageAmount;
	private float MortgageAmountWithFee;
	private int NumOfPaymment;
	private int PaymentNumber;
	private LocalDateTime MortgageStartDate;
	private int DayPaymenPerMonth;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMortgageId() {
		return MortgageId;
	}

	public void setMortgageId(String mortgageId) {
		MortgageId = mortgageId;
	}

	public float getMortgageAmount() {
		return MortgageAmount;
	}

	public void setMortgageAmount(float mortgageAmount) {
		MortgageAmount = mortgageAmount;
	}

	public float getMortgageAmountWithFee() {
		return MortgageAmountWithFee;
	}

	public void setMortgageAmountWithFee(float mortgageAmountWithFee) {
		MortgageAmountWithFee = mortgageAmountWithFee;
	}

	public int getNumOfPaymment() {
		return NumOfPaymment;
	}

	public void setNumOfPaymment(int numOfPaymment) {
		NumOfPaymment = numOfPaymment;
	}

	public int getPaymentNumber() {
		return PaymentNumber;
	}

	public void setPaymentNumber(int paymentNumber) {
		PaymentNumber = paymentNumber;
	}

	public LocalDateTime getMortgageStartDate() {
		return MortgageStartDate;
	}

	public void setMortgageStartDate(LocalDateTime mortgageStartDate) {
		MortgageStartDate = mortgageStartDate;
	}

	public int getDayPaymenPerMonth() {
		return DayPaymenPerMonth;
	}

	public void setDayPaymenPerMonth(int dayPaymenPerMonth) {
		DayPaymenPerMonth = dayPaymenPerMonth;
	}

	public Mortgage(String id, String mortgageId, float mortgageAmount, float mortgageAmountWithFee, int numOfPaymment,
			int paymentNumber, LocalDateTime mortgageStartDate, int dayPaymenPerMonth) {
		super();
		this.id = id;
		MortgageId = mortgageId;
		MortgageAmount = mortgageAmount;
		MortgageAmountWithFee = mortgageAmountWithFee;
		NumOfPaymment = numOfPaymment;
		PaymentNumber = paymentNumber;
		MortgageStartDate = mortgageStartDate;
		DayPaymenPerMonth = dayPaymenPerMonth;
	}

	public String toString() {
		return String.valueOf(MortgageAmount);
	}

}
