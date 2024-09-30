package Entity;

import java.sql.Date;

public class MortgageRequest {

	private int requestId;
	private String id;
	private float mortgageAmount;
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

	public float getMortgageAmount() {
		return mortgageAmount;
	}

	public void setMortgageAmount(Float mortgageAmount) {
		this.mortgageAmount = mortgageAmount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public MortgageRequest(int requestId, String id, float mortgageAmount, int numbersOfPayments, Date requestDate) {
		super();
		this.requestId = requestId;
		this.id = id;
		this.mortgageAmount = mortgageAmount;
		this.numbersOfPayments = numbersOfPayments;
		this.requestDate = requestDate;
	}

}
