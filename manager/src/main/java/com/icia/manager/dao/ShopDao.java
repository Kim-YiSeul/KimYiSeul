package com.icia.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.manager.model.Shop;

@Repository("shopDao")
public interface ShopDao 
{
	//매장 리스트
	public List<Shop> shopList(Shop shop);		
	
	//총 매장 수
	public long shopListCount(Shop shop);
	
	//매장 조회
	public Shop shopSelect(String shopUID);
	
	//매장 수정
	public int shopUpdate(Shop shop);
	
	//매장 등록
	public int shopInsert(Shop shop);

}
