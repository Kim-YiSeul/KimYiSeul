package com.icia.manager.model;

import java.io.Serializable;

public class ShopFile implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String shopUID;			//매장 고유번호
	private long shopFileSeq;		//파일번호
	private String shopFileOrgName;	//원본파일명
	private String shopFileName;	//파일명
	private String shopFileExt;		//파일확장자
	private long shopFileSize;		//파일사이즈
	private String shopFileRegDate;	//등록일
	
	
	public ShopFile() {
		shopUID = "";
		shopFileSeq = 0;
		shopFileOrgName = "";
		shopFileName = "";
		shopFileExt = "";
		shopFileSize = 0;
		shopFileRegDate = "";
	}


	public String getShopUID() {
		return shopUID;
	}


	public void setShopUID(String shopUID) {
		this.shopUID = shopUID;
	}


	public long getShopFileSeq() {
		return shopFileSeq;
	}


	public void setShopFileSeq(long shopFileSeq) {
		this.shopFileSeq = shopFileSeq;
	}


	public String getShopFileOrgName() {
		return shopFileOrgName;
	}


	public void setShopFileOrgName(String shopFileOrgName) {
		this.shopFileOrgName = shopFileOrgName;
	}


	public String getShopFileName() {
		return shopFileName;
	}


	public void setShopFileName(String shopFileName) {
		this.shopFileName = shopFileName;
	}


	public String getShopFileExt() {
		return shopFileExt;
	}


	public void setShopFileExt(String shopFileExt) {
		this.shopFileExt = shopFileExt;
	}


	public long getShopFileSize() {
		return shopFileSize;
	}


	public void setShopFileSize(long shopFileSize) {
		this.shopFileSize = shopFileSize;
	}


	public String getShopFileRegDate() {
		return shopFileRegDate;
	}


	public void setShopFileRegDate(String shopFileRegDate) {
		this.shopFileRegDate = shopFileRegDate;
	}
	
}
