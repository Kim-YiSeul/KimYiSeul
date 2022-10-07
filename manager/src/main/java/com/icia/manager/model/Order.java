package com.icia.manager.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orderUID;		//예약번호
	private String shopUID;			//매장번호
	private String userUID;			//예약자번호
	private String userId;			//예약자아이디
	private long reservationPeople;	//예약인원
	private String orderStatus;		//결제상태 (R:예약 중, E:예약만료, C:예약취소)
	private String payType;			//결제타입
	private long totalAmount;		//총액
	private String paymentKey;		//토스key
	private String regDate;			//예약일
	private String rDate;			//예약년월일시
	private String shopName;		//매장이름
	private String userName;		//예약자명
	
	private long startRow;			//시작 rownum
	private long endRow;			//끝 rownum
	
	private String searchType;		//조회항목(1:매장이름, 2:예약자명, 3:예약번호)	
	private String searchValue;		//검색값

	private long rNum;				//RNUM
	
	private List<OrderMenu> orderMenu;
	private ShopReservationTable shopReservationTable;
	
	public Order() {
		
		orderUID = "";
		shopUID = "";
		userUID = "";
		userId = "";
		reservationPeople = 0;
		orderStatus = "";
		payType = "";
		totalAmount = 0;
		paymentKey = "";
		regDate = "";
		rDate = "";
		shopName = "";
		userName = "";
		
		startRow = 0;
		endRow = 0;
		
		searchType = "";
		searchValue = "";
		
		rNum = 0;
		
		orderMenu = null;
		shopReservationTable = null;
	}

	
	public String getOrderUID() {
		return orderUID;
	}

	public void setOrderUID(String orderUID) {
		this.orderUID = orderUID;
	}

	public String getShopUID() {
		return shopUID;
	}

	public void setShopUID(String shopUID) {
		this.shopUID = shopUID;
	}

	public String getUserUID() {
		return userUID;
	}

	public void setUserUID(String userUID) {
		this.userUID = userUID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getReservationPeople() {
		return reservationPeople;
	}

	public void setReservationPeople(long reservationPeople) {
		this.reservationPeople = reservationPeople;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentKey() {
		return paymentKey;
	}

	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getrDate() {
		return rDate;
	}

	public void setrDate(String rDate) {
		this.rDate = rDate;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getStartRow() {
		return startRow;
	}

	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}

	public long getEndRow() {
		return endRow;
	}

	public void setEndRow(long endRow) {
		this.endRow = endRow;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public long getrNum() {
		return rNum;
	}

	public void setrNum(long rNum) {
		this.rNum = rNum;
	}

	public ShopReservationTable getShopReservationTable() {
		return shopReservationTable;
	}

	public void setShopReservationTable(ShopReservationTable shopReservationTable) {
		this.shopReservationTable = shopReservationTable;
	}


	public List<OrderMenu> getOrderMenu() {
		return orderMenu;
	}

	public void setOrderMenu(List<OrderMenu> orderMenu) {
		this.orderMenu = orderMenu;
	}
}