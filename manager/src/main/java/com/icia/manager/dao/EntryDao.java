package com.icia.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.manager.model.Entry;

@Repository("entryDao")
public interface EntryDao 
{
	//입점문의 리스트
	public List<Entry> entryList(Entry entry);	
	
	//총 입점문의 수
	public long entryListCount(Entry entry);
	
	//입점문의 조회
	public Entry entrySelect(long entrySeq);
	
	//입점문의 수정
	public int entryUpdate(Entry entry);

}