package Entity;

import java.sql.Date;

public class CreditRequest {

	private int requestId;
	private String id;
	private float creditAmount;
	private Date requestDate;

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

	public float getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Float creditAmount) {
		this.creditAmount = creditAmount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public CreditRequest(int requestId, String id, float creditAmount, Date requestDate) {
		super();
		this.requestId = requestId;
		this.id = id;
		this.creditAmount = creditAmount;
		this.requestDate = requestDate;
	}

}
