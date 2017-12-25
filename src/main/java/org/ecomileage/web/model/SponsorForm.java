package org.ecomileage.web.model;

public class SponsorForm {
	private Integer id;
	private Integer membershipFeeTypeId;
	private Integer membershipFeeId;
	private Integer membershipFee;
	private Integer bankId;
	private String accountNumber;
	private String sponsorDate;
	private String accountName;
	private Boolean isReviceReceipt;

	private String username;
	private String fullname;
	private String phone;
	private String email;
	private String postcode;
	private String address;
	private String addressDetail;
	private String dayOfBirth;
	private String reviceUserName;
	private String identityCardNumber1;
	private String identityCardNumber2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMembershipFeeTypeId() {
		return membershipFeeTypeId;
	}

	public void setMembershipFeeTypeId(Integer membershipFeeTypeId) {
		this.membershipFeeTypeId = membershipFeeTypeId;
	}

	public Integer getMembershipFeeId() {
		return membershipFeeId;
	}

	public void setMembershipFeeId(Integer membershipFeeId) {
		this.membershipFeeId = membershipFeeId;
	}

	public Integer getMembershipFee() {
		return membershipFee;
	}

	public void setMembershipFee(Integer membershipFee) {
		this.membershipFee = membershipFee;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSponsorDate() {
		return sponsorDate;
	}

	public void setSponsorDate(String sponsorDate) {
		this.sponsorDate = sponsorDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Boolean getIsReviceReceipt() {
		return isReviceReceipt;
	}

	public void setIsReviceReceipt(Boolean isReviceReceipt) {
		this.isReviceReceipt = isReviceReceipt;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getReviceUserName() {
		return reviceUserName;
	}

	public void setReviceUserName(String reviceUserName) {
		this.reviceUserName = reviceUserName;
	}

	public String getIdentityCardNumber1() {
		return identityCardNumber1;
	}

	public void setIdentityCardNumber1(String identityCardNumber1) {
		this.identityCardNumber1 = identityCardNumber1;
	}

	public String getIdentityCardNumber2() {
		return identityCardNumber2;
	}

	public void setIdentityCardNumber2(String identityCardNumber2) {
		this.identityCardNumber2 = identityCardNumber2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SponsorForm [id=" + id + ", membershipFeeTypeId=" + membershipFeeTypeId + ", membershipFeeId="
				+ membershipFeeId + ", membershipFeeOther=" + membershipFee + ", bankId=" + bankId
				+ ", accountNumber=" + accountNumber + ", sponsorDate=" + sponsorDate + ", accountName=" + accountName
				+ ", isReviceReceipt=" + isReviceReceipt + ", username=" + username + ", fullname=" + fullname
				+ ", phone=" + phone + ", email=" + email + ", postcode=" + postcode + ", address=" + address
				+ ", addressDetail=" + addressDetail + ", dayOfBirth=" + dayOfBirth + ", reviceUserName="
				+ reviceUserName + ", identityCardNumber1=" + identityCardNumber1 + ", identityCardNumber2="
				+ identityCardNumber2 + "]";
	}

}
