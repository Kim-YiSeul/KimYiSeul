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
import com.icia.manager.model.Order;
import com.icia.manager.model.Paging;
import com.icia.manager.model.Response;
import com.icia.manager.service.OrderService;

@Controller("orderController")
public class OrderController 
{
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 5;

	//예약 리스트
	@RequestMapping(value="/order/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Order order = new Order();
		//결제상태(R:예약 중, E:예약만료, C:예약취소, X:당일/하루전 예약취소)
		String orderStatus = HttpUtil.get(request, "orderStatus");
		//조회항목(1:매장이름, 2:예약자명, 3:예약번호)	
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
		//현재페이지
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		//총 게시물 수
		long totalCount = 0;
		//예약내역 리스트
		List<Order> list = null;
		//페이징 객체
		Paging paging = null;
		//결제상태
		order.setOrderStatus(orderStatus);
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				order.setShopName(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				order.setUserName(searchValue);
			}
			else if(StringUtil.equals(searchType, "3"))
			{
				order.setOrderUID(searchValue);
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

		totalCount = orderService.orderListCount(order);
		
		if(totalCount > 0)
		{
			paging = new Paging("/order/list", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
		
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			paging.addParam("orderStatus", orderStatus);
			paging.addParam("curPage", curPage);
			
			order.setStartRow(paging.getStartRow());
			order.setEndRow(paging.getEndRow());

			list = orderService.orderList(order);
			
		}
		
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("orderStatus", orderStatus);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		
		return "/order/list";
	}
	
	//예약 조회 수정
	@RequestMapping(value="/order/update")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Order order = new Order();
		//예약번호
  		String orderUID = HttpUtil.get(request, "orderUID", "");
		//결제상태(R:예약 중, E:예약만료, C:예약취소, X:당일/하루전 예약취소)
		String orderStatus = HttpUtil.get(request, "orderStatus");
		//조회항목(1:매장이름, 2:예약자명, 3:예약번호)	
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
  		//현재페이지
  		long curPage = HttpUtil.get(request, "curPage", (long)1);
  		//결제상태
		order.setOrderStatus(orderStatus);
		
		if(!StringUtil.isEmpty(orderUID)) 
		{	  			
			order = orderService.orderSelect(orderUID);
			
			if(order != null)
			{
				model.addAttribute("order", order);
			}
		}
		model.addAttribute("searchType", searchType);
  		model.addAttribute("searchValue", searchValue);
  		model.addAttribute("orderStatus", orderStatus);
  		model.addAttribute("curPage", curPage);
  		
		return "/order/update";
	}
	
	//예약정보 수정
	@RequestMapping(value="/order/updateProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateProc(HttpServletRequest request, HttpServletResponse response)
	{
		//예약번호
		String orderUID = HttpUtil.get(request, "orderUID");
		//매장번호
		String shopUID = HttpUtil.get(request, "shopUID");
		//예약자번호
		String userUID = HttpUtil.get(request, "userUID");
		//예약인원
		long reservationPeople = HttpUtil.get(request, "reservationPeople", (long)0);
		//결제상태(R:예약 중, E:예약만료, C:예약취소, X:당일/하루전 예약취소)
		String orderStatus = HttpUtil.get(request, "orderStatus");
		//결제타입
		String payType = HttpUtil.get(request, "payType");
		//총액
		long totalAmount = HttpUtil.get(request, "totalAmount", (long)0);
		//토스key
		String paymentKey = HttpUtil.get(request, "paymentKey");
		//예약일
		String rDate = HttpUtil.get(request, "rDate");
		//매장이름
		String shopName = HttpUtil.get(request, "shopName");
		//예약자명
		String userName = HttpUtil.get(request, "userName");

		Response<Object> ajaxResponse = new Response<Object>();
		
		if(!StringUtil.isEmpty(orderUID))
		{
			Order order = orderService.orderSelect(orderUID);
			
			if(order != null)
			{
				order.setOrderUID(orderUID);
				order.setShopUID(shopUID);
				order.setUserUID(userUID);
				order.setReservationPeople(reservationPeople);
				order.setOrderStatus(orderStatus);
				order.setPayType(payType);
				order.setTotalAmount(totalAmount);
				order.setPaymentKey(paymentKey);
				order.setrDate(rDate);
				order.setShopName(shopName);
				order.setUserName(userName);
				
				if(orderService.orderUpdate(order) > 0)
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
				ajaxResponse.setResponse(404, "Not Found"); // 예약 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
      
		if(logger.isDebugEnabled())
		{
			logger.debug("[OrderController] /order/updateProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
      
		return ajaxResponse;
	}

}
