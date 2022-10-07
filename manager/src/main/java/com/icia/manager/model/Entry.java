package com.icia.manager.model;

import java.io.Serializable;

public class Entry implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private long entrySeq;		//문의번호
	private String shopName;	//매장명
	private String userName;    //문의자
	private String userPhone;	//문의자 연락처
	private String userEmail;	//문의자 연락이메일
	private String agreement;	//약관동의여부
	private String status;		//처리사항(I:문의,A:접수,P:처리,C:완료)
	private String resultStatus;//승인여부(Y:승인,N:미승인)
	private String regDate;		//문의일시
	
	private long rNum;			//문의번호 RNUM
	
	private long startRow;		//시작 rownum
	private long endRow;		//끝 rownum
	
	private String searchType;	//조회항목(1:매장명, 2:문의자)
	private String searchValue;	//조회값

	
	public Entry()
	{
		entrySeq = 0;
		shopName = "";
		userName = "";
		userPhone = "";
		userEmail = "";
		agreement = "";
		status = "";
		resultStatus = "";
		regDate = "";
		
		rNum = 0;
		
		startRow = 0;
		endRow = 0;
		
		searchType = "";
		searchValue = "";
	}


	public long getEntrySeq() {
		return entrySeq;
	}


	public void setEntrySeq(long entrySeq) {
		this.entrySeq = entrySeq;
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


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getAgreement() {
		return agreement;
	}


	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	public long getrNum() {
		return rNum;
	}


	public void setrNum(long rNum) {
		this.rNum = rNum;
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
	
}