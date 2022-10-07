package com.icia.manager.model;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String userUID;		//회원고유번호
	private String userId;		//사용자 아이디
	private String userPwd;		//비밀번호
	private String userPhone;	//전화번호
	private String userName;	//사용자이름
	private String userNick;	//사용자 닉네임
	private String userEmail;	//사용자 이메일 
	private String adminStatus;	//관리자 상태 (Y:사용, N:정지)
	private String status;		//상태 (Y:사용, N:정지)
	private String regDate;		//등록일

	private String bizNum;		//사업자번호
	private String bizName;		//사업자명

	private UserFile userFile;	//첨부파일
	
	private String markUserUID;	//즐겨찾기 대상자
	
	private long startRow;		//시작 rownum
	private long endRow;		//끝 rownum
	private long rNum;			//RNUM

	public User()
	{
		userUID = "";
		userId = "";
		userPwd = "";
		userPhone = "";
		userName = "";
		userNick = "";
		userEmail = "";
		adminStatus = "";
		status = "";
		regDate = "";
		userFile = null;
		bizNum = "";
		bizName = "";
		
		markUserUID = "";
		
		startRow = 0;
		endRow = 0;
		rNum = 0;
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

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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

	public UserFile getUserFile() {
		return userFile;
	}

	public void setUserFile(UserFile userFile) {
		this.userFile = userFile;
	}

	public String getMarkUserUID() {
		return markUserUID;
	}

	public void setMarkUserUID(String markUserUID) {
		this.markUserUID = markUserUID;
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

	public long getrNum() {
		return rNum;
	}

	public void setrNum(long rNum) {
		this.rNum = rNum;
	}
	
}
