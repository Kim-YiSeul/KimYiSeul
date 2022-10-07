package com.icia.manager.model;

import java.io.Serializable;

public class Report implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private long bbsSeq;		//게시물고유번호
	private String userUID;		//회원고유번호
	private String report1;		//스팸
	private String report2;		//성격에 맞지 않는 글
	private String report3;		//과도한 욕설
	private String report4;		//분위기를 어지럽히는 글
	private String etcReport;	//기타신고사요
	private String regDate;		//신고 등록일

	private String userId;     // 사용자 아이디
	private String userNick;	//사용자 닉네임
	private String bbsTitle;	//게시물 제목
	private String bbsContent;	//내용
	
	private long rNum;			//RNUM
	
	private long startRow;		//시작 rownum
	private long endRow;		//끝 rownum
	
	private String searchType;	//조회항목(1:게시물 작성자, 2:게시물제목, 3:게시물내용)
	private String searchValue;	//조회값
	
	private BoardFile boardFile;//첨부파일
	
	public Report()
	{
		bbsSeq = 0;
		userUID = "";
		report1 = "";
		report2 = "";
		report3 = "";
		report4 = "";
		etcReport = "";
		regDate = "";

		userId = "";
		userNick = "";
		bbsTitle = "";
		bbsContent = "";
		
		rNum = 0;
		
		startRow = 0;
		endRow = 0;
		
		searchType = "";
		searchValue = "";
		
		boardFile = null;
	}

	
	public long getBbsSeq() {
		return bbsSeq;
	}

	public void setBbsSeq(long bbsSeq) {
		this.bbsSeq = bbsSeq;
	}

	public String getUserUID() {
		return userUID;
	}

	public void setUserUID(String userUID) {
		this.userUID = userUID;
	}

	public String getReport1() {
		return report1;
	}

	public void setReport1(String report1) {
		this.report1 = report1;
	}

	public String getReport2() {
		return report2;
	}

	public void setReport2(String report2) {
		this.report2 = report2;
	}

	public String getReport3() {
		return report3;
	}

	public void setReport3(String report3) {
		this.report3 = report3;
	}

	public String getReport4() {
		return report4;
	}

	public void setReport4(String report4) {
		this.report4 = report4;
	}

	public String getEtcReport() {
		return etcReport;
	}

	public void setEtcReport(String etcReport) {
		this.etcReport = etcReport;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getBbsTitle() {
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}

	public String getBbsContent() {
		return bbsContent;
	}

	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
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

	public BoardFile getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(BoardFile boardFile) {
		this.boardFile = boardFile;
	}
	
}