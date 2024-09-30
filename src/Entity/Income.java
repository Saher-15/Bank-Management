package Entity;

import java.time.LocalDateTime;

public class Income {
	private int transferId;
	private String srcId;
	private String destId;
	private float amount;
	private LocalDateTime transferDate;

	public Income(int transferId, String srcId, String destId, float amount, LocalDateTime transferDate) {
		super();
		this.transferId = transferId;
		this.srcId = srcId;
		this.destId = destId;
		this.amount = amount;
		this.transferDate = transferDate;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getDestId() {
		return destId;
	}

	public void setDestId(String destId) {
		this.destId = destId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(LocalDateTime transferDate) {
		this.transferDate = transferDate;
	}
}
