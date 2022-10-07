package com.icia.manager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.manager.dao.EntryDao;
import com.icia.manager.model.Entry;

@Service("entryService")
public class EntryService 
{
	private static Logger logger = LoggerFactory.getLogger(EntryService.class);
	
	@Autowired
	private EntryDao entryDao;
	
	//입점문의 리스트
	public List<Entry> entryList(Entry entry)
	{
		List<Entry> list = null;
		
		try
		{
			list = entryDao.entryList(entry);
		}
		catch(Exception e)
		{
			logger.error("[entryService] entryList Exception", e);
		}
		
		return list;
	}
	
	//총 입점문의 수
	public long entryListCount(Entry entry)
	{
		long count = 0;
		
		try
		{
			count = entryDao.entryListCount(entry);
		}
		catch(Exception e)
		{
			logger.error("[entryService] entryListCount Exception", e);
		}
		return count;
	}
	
	//입점문의 조회
	public Entry entrySelect(long entrySeq)
	{
		Entry entry = null;
		
		try
		{
			entry = entryDao.entrySelect(entrySeq);
		}
		catch(Exception e)
		{
			logger.error("[entryService] entrySelect Exception", e);
		}
		
		return entry;
	}
	
	//입점문의 수정
	public int entryUpdate(Entry entry)
	{
		int count = 0;
		
		try
		{
			count = entryDao.entryUpdate(entry);
		}
		catch(Exception e)
		{
			logger.error("[entryService] entryUpdate Exception", e);
		}
		
		return count;
	}
}
