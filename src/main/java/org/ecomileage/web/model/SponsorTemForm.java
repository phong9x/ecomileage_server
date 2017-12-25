package org.ecomileage.web.model;

public class SponsorTemForm {
	private String username;
	private String fullname;
	private String phone;
	private String postcode;
	private String address;
	private String addressDetail;
	private String email;
	private String createDates;
	private Integer money;
	private String paymentMethod;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getCreateDates() {
		return createDates;
	}

	public void setCreateDates(String createDates) {
		this.createDates = createDates;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "SponsorTemForm [username=" + username + ", fullname=" + fullname + ", phone=" + phone + ", postcode="
				+ postcode + ", address=" + address + ", addressDetail=" + addressDetail + ", email=" + email
				+ ", createDates=" + createDates + ", money=" + money + ", paymentMethod=" + paymentMethod + "]";
	}

}
