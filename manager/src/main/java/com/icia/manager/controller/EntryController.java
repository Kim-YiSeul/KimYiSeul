package com.icia.manager.controller;

import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.common.util.HttpUtil;
import com.icia.common.util.JsonUtil;
import com.icia.common.util.StringUtil;
import com.icia.manager.model.Entry;
import com.icia.manager.model.Paging;
import com.icia.manager.model.Response;
import com.icia.manager.model.Shop;
import com.icia.manager.service.EntryService;
import com.icia.manager.service.ShopService;

@Controller("entryController")
public class EntryController 
{
	private static Logger logger = LoggerFactory.getLogger(EntryController.class);
	
	@Autowired
	private EntryService entryService;
	@Autowired
	private ShopService shopService;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 5;

	//입점문의 리스트
	@RequestMapping(value="/entry/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Entry entry = new Entry();
		//처리사항(I:문의,A:접수,P:처리,C:완료)
		String status = HttpUtil.get(request, "status");
		//승인여부(Y:승인,N:미승인)
		String resultStatus = HttpUtil.get(request, "resultStatus");
		//조회항목(1:매장명, 2:문의자)
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
		//현재페이지
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		//총 게시물 수
		long totalCount = 0;
		//입점문의 내역 리스트
		List<Entry> list = null;
		//페이징 객체
		Paging paging = null;
		
		//처리사항
		entry.setStatus(status);
		//승인여부
		entry.setResultStatus(resultStatus);
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				entry.setShopName(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				entry.setUserName(searchValue);
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

		totalCount = entryService.entryListCount(entry);
		
		if(totalCount > 0)
		{
			paging = new Paging("/entry/list", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
		
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			paging.addParam("status", status);
			paging.addParam("resultStatus", resultStatus);
			paging.addParam("curPage", curPage);
			
			entry.setStartRow(paging.getStartRow());
			entry.setEndRow(paging.getEndRow());

			list = entryService.entryList(entry);
			
		}
		
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("status", status);
		model.addAttribute("resultStatus", resultStatus);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		
		return "/entry/list";
	}
	
	//입점문의 조회 수정
	@RequestMapping(value="/entry/update")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Entry entry = new Entry();
		//문의번호
		long entrySeq = HttpUtil.get(request, "entrySeq", (long)0);
		//조회항목(1:매장이름, 2:예약자명, 3:예약번호)	
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
  		//현재페이지
  		long curPage = HttpUtil.get(request, "curPage", (long)1);
  		//처리사항(I:문의,A:접수,P:처리,C:완료)
		String status = HttpUtil.get(request, "status");
		//승인여부(Y:승인,N:미승인)
		String resultStatus = HttpUtil.get(request, "resultStatus");
  		//처리사항
		entry.setStatus(status);
		//승인여부
		entry.setResultStatus(resultStatus);
		
		if(!StringUtil.isEmpty(entrySeq)) 
		{	  			
			entry = entryService.entrySelect(entrySeq);
			
			if(entry != null)
			{
				model.addAttribute("entry", entry);
			}
		}
		model.addAttribute("searchType", searchType);
  		model.addAttribute("searchValue", searchValue);
		model.addAttribute("status", status);
		model.addAttribute("resultStatus", resultStatus);
  		model.addAttribute("curPage", curPage);
  		
		return "/entry/update";
	}
	
	//입점문의정보 수정
	@RequestMapping(value="/entry/updateProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateProc(HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Entry entry = new Entry();
		//문의번호
		long entrySeq = HttpUtil.get(request, "entrySeq", (long)0);
		//처리사항(I:문의,A:접수,P:처리,C:완료)
		String status = HttpUtil.get(request, "status");
		//승인여부(Y:승인,N:미승인)
		String resultStatus = HttpUtil.get(request, "resultStatus");
		//매장명
		String shopName = HttpUtil.get(request, "shopName");
		//문의자
		String userName = HttpUtil.get(request, "userName");
		//문의자 연락처
		String userPhone = HttpUtil.get(request, "userPhone");
		//문의자 연락이메일
		String userEmail = HttpUtil.get(request, "userEmail");
		//약관동의여부
		String agreement = HttpUtil.get(request, "agreement");
		//입점문의 등록일
  		String regDate = HttpUtil.get(request, "regDate");
		
		Response<Object> ajaxResponse = new Response<Object>();
		
		if(!StringUtil.isEmpty(shopName) && !StringUtil.isEmpty(userName) 
				&& !StringUtil.isEmpty(userPhone) && !StringUtil.isEmpty(userEmail))
		{
			entry = entryService.entrySelect(entrySeq);
			
			if(entry != null)
			{				
				entry.setEntrySeq(entrySeq);
				entry.setShopName(shopName);
				entry.setUserName(userName);
				entry.setUserPhone(userPhone);
				entry.setUserEmail(userEmail);
				entry.setAgreement(agreement);
				entry.setStatus(status);
				entry.setResultStatus(resultStatus);
				entry.setRegDate(regDate);
				
				if(entryService.entryUpdate(entry) > 0)
				{
					ajaxResponse.setResponse(0, "success"); // 성공
				}
				else
				{
					ajaxResponse.setResponse(-1, "fail"); // 오류
				}
			}
			else
			{
				ajaxResponse.setResponse(404, "Not Found"); // 입점문의 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
      
		if(logger.isDebugEnabled())
		{
			logger.debug("[EntryController] /entry/updateProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
      
		return ajaxResponse;
	}
	
	HttpSession session;
	   @ModelAttribute
		void init(HttpServletRequest request, Model model) {
			
			this.session = request.getSession();
		}
	   
	//입점문의정보 등록
	@RequestMapping(value="/entry/shopInsertProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> shopInsertProc(HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Shop shop = new Shop();
		Entry entry = new Entry();
		//문의번호
		long entrySeq = HttpUtil.get(request, "entrySeq", (long)0);
		//매장UID, 유저UID 등록
		String shopUID = StringUtil.getNumId();
		String userUID = StringUtil.getNumId();
				
		Response<Object> ajaxResponse = new Response<Object>();
		
		if(!StringUtil.isEmpty(entrySeq))
		{
			entry = entryService.entrySelect(entrySeq);
			shop.setUserUID(userUID);
			shop.setShopUID(shopUID);
			session.setAttribute("code", shopUID);
			if(entry != null)
			{			
				try
		        {
		            if(shopService.shopInsert(shop) > 0)
		            {
		            	ajaxResponse.setResponse(0, "success"); // 성공
		            }
		            else
		            {
		            	ajaxResponse.setResponse(-1, "fail"); // 오류
		            }
		        }
		        catch(Exception e)
		        {
		        	ajaxResponse.setResponse(-1, "fail"); // 오류
		        }
			}
			else
			{
				ajaxResponse.setResponse(404, "Not Found"); // 입점문의 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
		
		if(logger.isDebugEnabled())
		{
			logger.debug("[EntryController] /entry/shopInsertProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
		
		return ajaxResponse;
	}
	
	//이메일 인증
    @Autowired
   private JavaMailSenderImpl mailSender;

   @ResponseBody
   @RequestMapping(value = "/emailAuth", method = RequestMethod.POST)
   public String emailAuth(String email) {      
	   String shopUID = (String) session.getAttribute("code");
	   session.removeAttribute("code");
      /* 이메일 보내기 */
        String setFrom = "yhh0420@naver.com";
        String toMail = email;
        String title = "안녕하세요. Empty Plate입니다.";
        String content = 
                "Empty Plate에 입점해주셔서 감사합니다." +
                "<br><br>" + 
                "인증번호는 " + shopUID + "입니다." + 
                "<br>" + 
                "해당 인증번호를 매장가입 인증번호란에 기입하여 주세요.";
        try {
           
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return shopUID;
 
   }

}
