package com.icia.manager.model;

import java.io.Serializable;

public class ShopReservationTable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String shopTableUID;		//테이블고유번호
	private String shopTableStatus;		//테이블예약상태(Y:예약완료, N:예약미완료)
	private String shopReservationDate;	//예약 년월일
	private String shopReservationTime;	//예약시간
	private String orderUID;			//예약번호
	
	
	public ShopReservationTable() {
		shopTableUID = "";
		shopTableStatus = "";
		shopReservationDate = "";
		shopReservationTime = "";
		orderUID = "";
	}


	public String getShopTableUID() {
		return shopTableUID;
	}


	public void setShopTableUID(String shopTableUID) {
		this.shopTableUID = shopTableUID;
	}


	public String getShopTableStatus() {
		return shopTableStatus;
	}


	public void setShopTableStatus(String shopTableStatus) {
		this.shopTableStatus = shopTableStatus;
	}


	public String getShopReservationDate() {
		return shopReservationDate;
	}


	public void setShopReservationDate(String shopReservationDate) {
		this.shopReservationDate = shopReservationDate;
	}


	public String getShopReservationTime() {
		return shopReservationTime;
	}


	public void setShopReservationTime(String shopReservationTime) {
		this.shopReservationTime = shopReservationTime;
	}


	public String getOrderUID() {
		return orderUID;
	}


	public void setOrderUID(String orderUID) {
		this.orderUID = orderUID;
	}
	
}
