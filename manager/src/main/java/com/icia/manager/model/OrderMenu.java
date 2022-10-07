package com.icia.manager.model;

import java.io.Serializable;

public class OrderMenu implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String orderUID;		//예약번호
	private String orderMenuName;	//예약메뉴명
	private long orderMenuPrice;	//예약메뉴가격
	private long orderMenuQuantity;	//예약메뉴수량
	
	public OrderMenu() {
		orderUID = "";
		orderMenuName = "";
		orderMenuPrice = 0;
		orderMenuQuantity = 0;
	}

	
	
	public String getOrderUID() {
		return orderUID;
	}

	public void setOrderUID(String orderUID) {
		this.orderUID = orderUID;
	}

	public String getOrderMenuName() {
		return orderMenuName;
	}

	public void setOrderMenuName(String orderMenuName) {
		this.orderMenuName = orderMenuName;
	}

	public long getOrderMenuPrice() {
		return orderMenuPrice;
	}

	public void setOrderMenuPrice(long orderMenuPrice) {
		this.orderMenuPrice = orderMenuPrice;
	}

	public long getOrderMenuQuantity() {
		return orderMenuQuantity;
	}

	public void setOrderMenuQuantity(long orderMenuQuantity) {
		this.orderMenuQuantity = orderMenuQuantity;
	}
	
}
