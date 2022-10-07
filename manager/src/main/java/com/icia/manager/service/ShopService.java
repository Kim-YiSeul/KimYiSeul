package com.icia.manager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.manager.dao.ShopDao;
import com.icia.manager.model.Shop;

@Service("shopService")
public class ShopService 
{
	private static Logger logger = LoggerFactory.getLogger(ShopService.class);
	
	@Autowired
	private ShopDao shopDao;
	
	//파일 저장 경로
	@Value("#{env['shop.upload.dir']}")
	private String SHOP_UPLOAD_DIR;
		
	//매장 리스트
	public List<Shop> shopList(Shop shop)
	{
		List<Shop> list = null;
		
		try
		{
			list = shopDao.shopList(shop);
		}
		catch(Exception e)
		{
			logger.error("[shopService] shopList Exception", e);
		}
		
		return list;
	}
	
	//총 매장 수
	public long shopListCount(Shop shop)
	{
		long count = 0;
		
		try
		{
			count = shopDao.shopListCount(shop);
		}
		catch(Exception e)
		{
			logger.error("[shopService] shopListCount Exception", e);
		}
		
		return count;
	}
	
	//매장 조회
	public Shop shopSelect(String shopUID)
	{
		Shop shop = null;
		
		try
		{
			shop = shopDao.shopSelect(shopUID);
		}
		catch(Exception e)
		{
			logger.error("[shopService] shopSelect Exception", e);
		}
		return shop;
	}
	
	//매장 수정
	public int shopUpdate(Shop shop)
	{
		int count = 0;
		
		try
		{
			count = shopDao.shopUpdate(shop);
		}
		catch(Exception e)
		{
			logger.error("[shopService] shopUpdate Exception", e);
		}
      
		return count;
	}
	
	//매장 등록
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int shopInsert(Shop shop) throws Exception
	{	
		int count = shopDao.shopInsert(shop);

		return count;
	}
	
}
