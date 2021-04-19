package com.bank.orion.model;

public class AccountHolder implements Item {

	private String dob;
	private String emailID;
	private String last4SSN;
	private String legalName;
	private String phone;
	private String userRole;
	private String holderStatus;
	private String userName;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getLast4SSN() {
		return last4SSN;
	}

	public void setLast4SSN(String last4ssn) {
		last4SSN = last4ssn;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getHolderStatus() {
		return holderStatus;
	}

	public void setHolderStatus(String holderStatus) {
		this.holderStatus = holderStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "AccountHolder [dob=" + dob + ", emailID=" + emailID + ", last4SSN=" + last4SSN + ", legalName="
				+ legalName + ", phone=" + phone + ", userRole=" + userRole + ", holderStatus=" + holderStatus
				+ ", userName=" + userName + "]";
	}

}
