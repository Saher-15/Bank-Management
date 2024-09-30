package Entity;

public class Frame {
	private String userId;
	private int frameId;
	private float frameAmount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public float getFrameAmount() {
		return frameAmount;
	}

	public void setFrameAmount(float frameAmount) {
		this.frameAmount = frameAmount;
	}

	public Frame(String userId, int frameId, float frameAmount) {
		super();
		this.userId = userId;
		this.frameId = frameId;
		this.frameAmount = frameAmount;
	}

}
