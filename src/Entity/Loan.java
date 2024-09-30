package Entity;

import java.time.LocalDateTime;


public class Loan {
	private String Id;
	private String LoanId;
	private float LoanAmount;
	private float LoanAmountWithFee;
	private int numbersOfPayments;
	private int PaymentNumber;
	private LocalDateTime LoanStartDate;
	private int DayPaymenPerMonth;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	public String getLoanid() {
		return LoanId;
	}

	public void setLoanid(String loanid) {
		LoanId = loanid;
	}

	public float getLoanAmount() {
		return LoanAmount;
	}

	public void setLoanAmount(float loanAmount) {
		LoanAmount = loanAmount;
	}

	public float getLoanAmountWithFee() {
		return LoanAmountWithFee;
	}

	public void setLoanAmountWithFee(float loanAmountWithFee) {
		LoanAmountWithFee = loanAmountWithFee;
	}

	public int getNumbersOfPayments() {
		return numbersOfPayments;
	}

	public void setNumbersOfPayments(int numbersOfPayments) {
		this.numbersOfPayments = numbersOfPayments;
	}

	public int getPaymentNumber() {
		return PaymentNumber;
	}

	public void setPaymentNumber(int paymentNumber) {
		PaymentNumber = paymentNumber;
	}

	public LocalDateTime getLoanStartDate() {
		return LoanStartDate;
	}

	public void setLoanStartDate(LocalDateTime loanStartDate) {
		LoanStartDate = loanStartDate;
	}

	public int getDayPaymenPerMonth() {
		return DayPaymenPerMonth;
	}

	public void setDayPaymenPerMonth(int dayPaymenPerMonth) {
		DayPaymenPerMonth = dayPaymenPerMonth;
	}

	public Loan(String id, String loanid, float loanAmount, float loanAmountWithFee, int numbersOfPayments,
			int paymentNumber, LocalDateTime loanStartDate, int dayPaymenPerMonth) {
		super();
		this.Id = id;
		LoanId = loanid;
		LoanAmount = loanAmount;
		LoanAmountWithFee = loanAmountWithFee;
		this.numbersOfPayments = numbersOfPayments;
		PaymentNumber = paymentNumber;
		LoanStartDate = loanStartDate;
		DayPaymenPerMonth = dayPaymenPerMonth;
	}

	public String toString() {
		return String.valueOf(LoanAmount);
	}

}
