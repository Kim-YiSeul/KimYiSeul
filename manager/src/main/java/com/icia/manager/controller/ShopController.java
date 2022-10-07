package com.icia.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.common.util.HttpUtil;
import com.icia.common.util.JsonUtil;
import com.icia.common.util.StringUtil;
import com.icia.manager.model.Paging;
import com.icia.manager.model.Response;
import com.icia.manager.model.Shop;
import com.icia.manager.service.ShopService;

@Controller("shopController")
public class ShopController 
{
	private static Logger logger = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	private ShopService shopService;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 5;

	//매장 리스트
	@RequestMapping(value="/shop/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Shop shop =  new Shop();
		//매장종류(1:파인다이닝, 2:오마카세)
		String shopType = HttpUtil.get(request, "shopType");
		//조회항목(1:매장이름, 2:매장주소, 3:매장번호)
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
		//현재페이지
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		//총 매장 수
		long totalCount = 0;
		//매장 리스트
		List<Shop> list = null;
		//페이징 객체
		Paging paging = null;
		
		//매장종류
		shop.setShopType(shopType);
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				shop.setShopName(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				shop.setShopAddress(searchValue);
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

		totalCount = shopService.shopListCount(shop);
		
		if(totalCount > 0)
		{
			paging = new Paging("/shop/list", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
		
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			paging.addParam("shopType", shopType);
			paging.addParam("curPage", curPage);
			
			shop.setStartRow(paging.getStartRow());
			shop.setEndRow(paging.getEndRow());

			list = shopService.shopList(shop);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("shopType", shopType);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		
		return "/shop/list";
	}
	
	//매장 조회 수정
	@RequestMapping(value="/shop/update")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회객체
		Shop shop = new Shop();
		//매장 번호
  		String shopUID = HttpUtil.get(request, "shopUID", "");
  		//조회항목(1:매장이름, 2:상세주소, 3:매장UID)	
  		String searchType = HttpUtil.get(request, "searchType", "");
  		//조회값
  		String searchValue = HttpUtil.get(request, "searchValue", "");
		//매장종류(1:파인다이닝, 2:오마카세)
		String shopType = HttpUtil.get(request, "shopType");
  		//현재페이지
  		long curPage = HttpUtil.get(request, "curPage", (long)1);
  		
  		//매장종류
		shop.setShopType(shopType);
		
		if(!StringUtil.isEmpty(shopUID)) 
		{	  			
			shop = shopService.shopSelect(shopUID);
			
			if(shop != null)
			{
				model.addAttribute("shop", shop);
			}
		}
		
		model.addAttribute("searchType", searchType);
  		model.addAttribute("searchValue", searchValue);
  		model.addAttribute("shopType", shopType);
  		model.addAttribute("curPage", curPage);
  		
		return "/shop/update";
	}
	
	//매장정보 수정
	@RequestMapping(value="/shop/updateProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateProc(HttpServletRequest request, HttpServletResponse response)
	{
		//매장 번호
		String shopUID = HttpUtil.get(request, "shopUID");
		//회원고유번호
		String userUID = HttpUtil.get(request, "userUID");
		//매장이름
		String shopName = HttpUtil.get(request, "shopName");
		//매장타입(1:파인다이닝, 2:오마카세)
		String shopType = HttpUtil.get(request, "shopType");
		//매장휴일 (-1:휴무없음,0:일,1:월,2:화,3:수,4:목,5:금,6:토)
		String shopHoliday = HttpUtil.get(request, "shopHoliday");
		//매장영업시간
		String shopOrderTime = HttpUtil.get(request, "shopOrderTime");
		//해시태그
		String shopHashtag = HttpUtil.get(request, "shopHashtag");
		//매장 도로명주소
		String shopLocation1 = HttpUtil.get(request, "shopLocation1");
		//매장 지번주소
		String shopLocation2 = HttpUtil.get(request, "shopLocation2");
		//매장 주소
		String shopAddress = HttpUtil.get(request, "shopAddress");
		//매장전화번호
		String shopTelephone = HttpUtil.get(request, "shopTelephone");
		//매장한줄소개
		String shopIntro = HttpUtil.get(request, "shopIntro");
		//매장내용
		String shopContent = HttpUtil.get(request, "shopContent");
		//매장등록일
		String regDate = HttpUtil.get(request, "regDate");
		//사업자번호
		String bizNum = HttpUtil.get(request, "bizNum");
		//사업자명
		String bizName = HttpUtil.get(request, "bizName");
      
		Response<Object> ajaxResponse = new Response<Object>();
		
		if(!StringUtil.isEmpty(shopUID))
		{
			Shop shop = shopService.shopSelect(shopUID);
			
			if(shop != null)
			{
				shop.setShopUID(shopUID);
				shop.setUserUID(userUID);
				shop.setShopName(shopName);
				shop.setShopType(shopType);
				shop.setShopHoliday(shopHoliday);
				shop.setShopOrderTime(shopOrderTime);
				shop.setShopHashtag(shopHashtag);
				shop.setShopLocation1(shopLocation1);
				shop.setShopLocation2(shopLocation2);
				shop.setShopAddress(shopAddress);
				shop.setShopTelephone(shopTelephone);
				shop.setShopIntro(shopIntro);
				shop.setShopContent(shopContent);
				shop.setRegDate(regDate);
				shop.setBizNum(bizNum);
				shop.setBizName(bizName);
				
				if(shopService.shopUpdate(shop) > 0)
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
				ajaxResponse.setResponse(404, "Not Found"); // 매장 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
      
		if(logger.isDebugEnabled())
		{
			logger.debug("[ShopController] /shop/updateProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
      
		return ajaxResponse;
	}

}
