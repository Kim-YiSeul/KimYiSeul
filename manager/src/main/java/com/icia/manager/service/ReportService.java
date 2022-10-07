package com.icia.manager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.manager.dao.ReportDao;
import com.icia.manager.model.Report;

@Service("reportService")
public class ReportService 
{
	private static Logger logger = LoggerFactory.getLogger(ReportService.class);
	
	@Autowired
	private ReportDao reportDao;
	
	//신고 리스트
	public List<Report> reportList(Report report)
	{
		List<Report> list = null;
		
		try
		{
			list = reportDao.reportList(report);
		}
		catch(Exception e)
		{
			logger.error("[ReportService] reportList Exception", e);
		}
		
		return list;
	}
	
	//총 신고 수
	public long reportListCount(Report report)
	{
		long count = 0;
		
		try
		{
			count = reportDao.reportListCount(report);
		}
		catch(Exception e)
		{
			logger.error("[ReportService] reportListCount Exception", e);
		}
		
		return count;
	}
	
}
