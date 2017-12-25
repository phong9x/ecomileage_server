package org.ecomileage.web.model;

public class TransferForm {
	private Integer id;
	private Short status;
	private String memo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "TransferForm [id=" + id + ", status=" + status + ", memo=" + memo + "]";
	}

}
