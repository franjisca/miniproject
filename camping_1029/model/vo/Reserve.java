package camping.reserve.model.vo;

import java.sql.Date;

public class Reserve {
	private int reserveNo;
	private int userNo;
	private String campName;
	private int numOfPeople;
	private java.sql.Date reservationDate; 
	
	public Reserve() {
		super();
	}

	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public int getNumOfPeople() {
		return numOfPeople;
	}

	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	public java.sql.Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(java.sql.Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Reserve(int reserveNo, int userNo, String campName, int numOfPeople, Date reservationDate) {
		super();
		this.reserveNo = reserveNo;
		this.userNo = userNo;
		this.campName = campName;
		this.numOfPeople = numOfPeople;
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return "Reserve [reserveNo=" + reserveNo + ", userNo=" + userNo + ", campName=" + campName + ", numOfPeople="
				+ numOfPeople + ", reservationDate=" + reservationDate + "]";
	}
}