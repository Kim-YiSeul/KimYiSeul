package com.icia.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icia.common.util.StringUtil;
import com.icia.common.util.HttpUtil;
import com.icia.manager.service.ReportService;
import com.icia.manager.model.Paging;
import com.icia.manager.model.Report;

@Controller("reportController")
public class ReportController 
{
	private static Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private ReportService reportService;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 5;
	
	//신고 리스트
	@RequestMapping(value="/report/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Report report = new Report();
		//조회항목(1:게시물 작성자, 2:게시물제목, 3:게시물내용)
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
		//현재페이지
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		//총 신고내역 수
		long totalCount = 0;
		//신고내역 리스트
		List<Report> list = null;
		//페이징 객체
		Paging paging = null;
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				report.setUserNick(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				report.setBbsTitle(searchValue);
			}
			else if(StringUtil.equals(searchType, "3"))
			{
				report.setBbsContent(searchValue);
			}
			else
			{
				searchType = "";
				searchValue = "";
			}
		}
		else
		{
			searchType = "";
			searchValue= "";
		}

		totalCount = reportService.reportListCount(report);
		
		if(totalCount > 0)
		{
			paging = new Paging("/report/list", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
			
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			paging.addParam("curPage", curPage);
			
			report.setStartRow(paging.getStartRow());
			report.setEndRow(paging.getEndRow());

			list = reportService.reportList(report);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		
		return "/report/list";
	}

}
