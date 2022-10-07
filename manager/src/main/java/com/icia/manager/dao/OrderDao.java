package com.icia.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.manager.model.Order;

@Repository("orderDao")
public interface OrderDao 
{
	//예약리스트
	public List<Order> orderList(Order order);
	
	//총 예약 수
	public long orderListCount(Order order);
	
	//예약 조회
	public Order orderSelect(String orderUID);
	
	//예약 수정
	public int orderUpdate(Order order);
}
