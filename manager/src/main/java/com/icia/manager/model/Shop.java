package com.icia.manager.model;

import java.io.Serializable;
import java.util.List;

public class Shop implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String shopUID;			//매장 고유번호
	private String userUID;			//회원고유번호
	private String userId;     		//사용자 아이디
	private String shopName;		//매장이름
	private String shopType;		//매장타입(1:파인다이닝, 2:오마카세)
	private String bizNum;	   		//사업자번호
	private String bizName;    		//사업자명
	private String shopHoliday;		//매장휴일 (-1:휴무없음,0:일,1:월,2:화,3:수,4:목,5:금,6:토)
	private String shopOrderTime;	//매장영업시간
	private String shopHashtag;		//해시태그
	private String shopLocation1;	//매장 도로명주소
	private String shopLocation2;	//매장 지번주소
	private String shopAddress;		//매장 주소
	private String shopTelephone;	//매장전화번호
	private String shopIntro;		//매장한줄소개
	private String shopContent;	 	//매장내용
	private String regDate;			//매장등록일
	
	private long startRow;			//시작 rownum
	private long endRow;			//끝 rownum
	
	private String searchType;		//조회항목(1:매장이름, 2:매장주소, 3:매장번호)
	private String searchValue;		//검색값

	private long rNum;				//게시물번호 RNUM
	
	private List<ShopMenu> shopMenu;
	
	public Shop() {
		shopUID = "";
		userUID = "";
		userId = "";
		shopName = "";
		shopType = "";
		bizNum = "";
		bizName = "";
		shopHoliday = "";
		shopOrderTime = "";
		shopHashtag = "";
		shopLocation1 = "";
		shopLocation2 = "";
		shopAddress = "";
		shopTelephone = "";
		shopIntro = "";
		shopContent = "";
		regDate = "";
		
		startRow = 0;
		endRow = 0;
		
		searchType = "";
		searchValue = "";
		
		rNum = 0;
		
		shopMenu = null;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getBizNum() {
		return bizNum;
	}

	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getShopHoliday() {
		return shopHoliday;
	}

	public void setShopHoliday(String shopHoliday) {
		this.shopHoliday = shopHoliday;
	}

	public String getShopOrderTime() {
		return shopOrderTime;
	}

	public void setShopOrderTime(String shopOrderTime) {
		this.shopOrderTime = shopOrderTime;
	}

	public String getShopHashtag() {
		return shopHashtag;
	}

	public void setShopHashtag(String shopHashtag) {
		this.shopHashtag = shopHashtag;
	}

	public String getShopLocation1() {
		return shopLocation1;
	}

	public void setShopLocation1(String shopLocation1) {
		this.shopLocation1 = shopLocation1;
	}

	public String getShopLocation2() {
		return shopLocation2;
	}

	public void setShopLocation2(String shopLocation2) {
		this.shopLocation2 = shopLocation2;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopTelephone() {
		return shopTelephone;
	}

	public void setShopTelephone(String shopTelephone) {
		this.shopTelephone = shopTelephone;
	}

	public String getShopIntro() {
		return shopIntro;
	}

	public void setShopIntro(String shopIntro) {
		this.shopIntro = shopIntro;
	}

	public String getShopContent() {
		return shopContent;
	}

	public void setShopContent(String shopContent) {
		this.shopContent = shopContent;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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

	public List<ShopMenu> getShopMenu() {
		return shopMenu;
	}

	public void setShopMenu(List<ShopMenu> shopMenu) {
		this.shopMenu = shopMenu;
	}

}
