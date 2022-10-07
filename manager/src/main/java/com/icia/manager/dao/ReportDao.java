package com.icia.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.manager.model.Report;

@Repository("reportDao")
public interface ReportDao 
{	
	//신고 리스트
	public List<Report> reportList(Report boardReport);		
	
	//총 게시물 수
	public long reportListCount(Report boardReport);
	
}