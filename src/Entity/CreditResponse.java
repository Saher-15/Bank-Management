package Entity;

import java.time.LocalDateTime;

public class CreditResponse {
	private int responseId;
	private int requestId;
	private String userId;
	private String status;
	private String svResponse;
	private String responseType;
	private LocalDateTime responseDate;

	public CreditResponse(int responseId, int requestId, String userId, String status, String svResponse,
			String responseType, LocalDateTime responseDate) {
		super();
		this.responseId = responseId;
		this.requestId = requestId;
		this.userId = userId;
		this.status = status;
		this.svResponse = svResponse;
		this.responseType = responseType;
		this.responseDate = responseDate;
	}

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSvResponse() {
		return svResponse;
	}

	public void setSvResponse(String svResponse) {
		this.svResponse = svResponse;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public LocalDateTime getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(LocalDateTime responseDate) {
		this.responseDate = responseDate;
	}
}
