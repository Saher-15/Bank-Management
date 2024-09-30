package Entity;

import java.sql.Date;

public class FrameRequest {

	private int requestId;
	private String id;
	private float frameAmount;
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

	public float getFrameAmount() {
		return frameAmount;
	}

	public void setFrameAmount(Float frameAmount) {
		this.frameAmount = frameAmount;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public FrameRequest(int requestId, String id, float frameAmount, Date requestDate) {
		super();
		this.requestId = requestId;
		this.id = id;
		this.frameAmount = frameAmount;
		this.requestDate = requestDate;
	}

}
