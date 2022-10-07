package com.icia.manager.model;

import java.io.Serializable;

public class ShopMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String shopUID;			//매장 고유번호
	private String shopMenuCode;	//메뉴코드
	private String shopMenuName;	//메뉴이름
	private int shopMenuPrice;		//메뉴가격
	
	public ShopMenu() {
		shopUID = "";
		shopMenuCode = "";
		shopMenuName = "";
		shopMenuPrice = 0;
	}

	
	public String getShopUID() {
		return shopUID;
	}

	public void setShopUID(String shopUID) {
		this.shopUID = shopUID;
	}

	public String getShopMenuCode() {
		return shopMenuCode;
	}

	public void setShopMenuCode(String shopMenuCode) {
		this.shopMenuCode = shopMenuCode;
	}

	public String getShopMenuName() {
		return shopMenuName;
	}

	public void setShopMenuName(String shopMenuName) {
		this.shopMenuName = shopMenuName;
	}

	public int getShopMenuPrice() {
		return shopMenuPrice;
	}

	public void setShopMenuPrice(int shopMenuPrice) {
		this.shopMenuPrice = shopMenuPrice;
	}
	
}
