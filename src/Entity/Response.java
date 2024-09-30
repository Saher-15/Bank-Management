package Entity;

import java.sql.Date;

public class Response {
	private int requestId;
	private String status;
	private Date responseDate;

	public Response(int requestId, String status, Date responseDate, String type) {
		super();
		this.requestId = requestId;
		this.status = status;
		this.responseDate = responseDate;
		this.type = type;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;
}
