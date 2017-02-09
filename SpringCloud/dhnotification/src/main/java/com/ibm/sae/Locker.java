package com.ibm.sae;

public class Locker
{
	private String email;
	private int lockerNumber;
	private double lockerCode;

	public Locker(String email, int lockerNumber, double lockerCode) {
		super();
		this.email = email;
		this.lockerNumber = lockerNumber;
		this.lockerCode = lockerCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLockerNumber() {
		return lockerNumber;
	}

	public void setLockerNumber(int lockerNumber) {
		this.lockerNumber = lockerNumber;
	}

	public double getLockerCode() {
		return lockerCode;
	}

	public void setLockerCode(double lockerCode) {
		this.lockerCode = lockerCode;
	}
	
	
}
