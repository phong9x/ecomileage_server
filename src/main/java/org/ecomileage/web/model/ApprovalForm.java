package org.ecomileage.web.model;

public class ApprovalForm {
	private Integer id;

	private String title;

	private String startTime;

	private String endTime;

	private Integer bikePointPerKm;

	private Float maxMileagePerDay;

	private Float maxMileagePerOnce;

	private Integer maxApprovalPerDay;

	private Integer approvalDateNumber;

	private Float minAverageSpeed;

	private Float maxAverageSpeed;

	private Float maxSpeed;

	private Integer problemPoint;

	private Integer maxAcceleration;

	private Integer tranferPointPerKm;

	private Integer maxApprovalTranferPerDay;

	private Integer favoritePointPerKm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getBikePointPerKm() {
		return bikePointPerKm;
	}

	public void setBikePointPerKm(Integer bikePointPerKm) {
		this.bikePointPerKm = bikePointPerKm;
	}

	public Float getMaxMileagePerDay() {
		return maxMileagePerDay;
	}

	public void setMaxMileagePerDay(Float maxMileagePerDay) {
		this.maxMileagePerDay = maxMileagePerDay;
	}

	public Float getMaxMileagePerOnce() {
		return maxMileagePerOnce;
	}

	public void setMaxMileagePerOnce(Float maxMileagePerOnce) {
		this.maxMileagePerOnce = maxMileagePerOnce;
	}

	public Integer getMaxApprovalPerDay() {
		return maxApprovalPerDay;
	}

	public void setMaxApprovalPerDay(Integer maxApprovalPerDay) {
		this.maxApprovalPerDay = maxApprovalPerDay;
	}

	public Integer getApprovalDateNumber() {
		return approvalDateNumber;
	}

	public void setApprovalDateNumber(Integer approvalDateNumber) {
		this.approvalDateNumber = approvalDateNumber;
	}

	public Float getMinAverageSpeed() {
		return minAverageSpeed;
	}

	public void setMinAverageSpeed(Float minAverageSpeed) {
		this.minAverageSpeed = minAverageSpeed;
	}

	public Float getMaxAverageSpeed() {
		return maxAverageSpeed;
	}

	public void setMaxAverageSpeed(Float maxAverageSpeed) {
		this.maxAverageSpeed = maxAverageSpeed;
	}

	public Float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Integer getProblemPoint() {
		return problemPoint;
	}

	public void setProblemPoint(Integer problemPoint) {
		this.problemPoint = problemPoint;
	}

	public Integer getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(Integer maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public Integer getTranferPointPerKm() {
		return tranferPointPerKm;
	}

	public void setTranferPointPerKm(Integer tranferPointPerKm) {
		this.tranferPointPerKm = tranferPointPerKm;
	}

	public Integer getMaxApprovalTranferPerDay() {
		return maxApprovalTranferPerDay;
	}

	public void setMaxApprovalTranferPerDay(Integer maxApprovalTranferPerDay) {
		this.maxApprovalTranferPerDay = maxApprovalTranferPerDay;
	}

	public Integer getFavoritePointPerKm() {
		return favoritePointPerKm;
	}

	public void setFavoritePointPerKm(Integer favoritePointPerKm) {
		this.favoritePointPerKm = favoritePointPerKm;
	}

	@Override
	public String toString() {
		return "ApprovalForm [id=" + id + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", bikePointPerKm=" + bikePointPerKm + ", maxMileagePerDay=" + maxMileagePerDay
				+ ", maxMileagePerOnce=" + maxMileagePerOnce + ", maxApprovalPerDay=" + maxApprovalPerDay
				+ ", approvalDateNumber=" + approvalDateNumber + ", minAverageSpeed=" + minAverageSpeed
				+ ", maxAverageSpeed=" + maxAverageSpeed + ", maxSpeed=" + maxSpeed + ", problemPoint=" + problemPoint
				+ ", maxAcceleration=" + maxAcceleration + ", tranferPointPerKm=" + tranferPointPerKm
				+ ", maxApprovalTranferPerDay=" + maxApprovalTranferPerDay + ", favoritePointPerKm="
				+ favoritePointPerKm + "]";
	}

}
