package com.icia.manager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.manager.dao.OrderDao;
import com.icia.manager.model.Order;

@Service("orderService")
public class OrderService 
{
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private OrderDao orderDao;
	
	//예약리스트
	public List<Order> orderList(Order order)
	{
		List<Order> list = null;
		
		try
		{
			list = orderDao.orderList(order);
		}
		catch(Exception e)
		{
			logger.error("[orderService] orderList Exception", e);
		}
		
		return list;
	}
	
	//총 예약 수
	public long orderListCount(Order order)
	{
		long count = 0;
		
		try
		{
			count = orderDao.orderListCount(order);
		}
		catch(Exception e)
		{
			logger.error("[orderService] orderListCount Exception", e);
		}
		return count;
	}
	
	//예약 조회
	public Order orderSelect(String orderUID)
	{
		Order order = null;
		
		try
		{
			order = orderDao.orderSelect(orderUID);
		}
		catch(Exception e)
		{
			logger.error("[orderService] orderSelect Exception", e);
		}
		
		return order;
	}
	
	//예약 수정
	public int orderUpdate(Order order)
	{
		int count = 0;
		
		try
		{
			count = orderDao.orderUpdate(order);
		}
		catch(Exception e)
		{
			logger.error("[orderService] orderUpdate Exception", e);
		}
		
		return count;
	}
}
