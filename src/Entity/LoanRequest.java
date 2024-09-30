package Entity;

import java.sql.Date;

public class LoanRequest {

	private int requestId;
	private String id;
	private float loanAmount;
	private int numbersOfPayments;
	private Date requestDate;

	public int getNumbersOfPayments() {
		return numbersOfPayments;
	}

	public void setNumbersOfPayments(int numbersOfPayments) {
		this.numbersOfPayments = numbersOfPayments;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public LoanRequest(int requestId, String id, float loanAmount, int numbersOfPayments, Date requestDate) {
		super();
		this.requestId = requestId;
		this.id = id;
		this.loanAmount = loanAmount;
		this.numbersOfPayments = numbersOfPayments;
		this.requestDate = requestDate;
	}

}
