package com.icia.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.common.util.CookieUtil;
import com.icia.common.util.HttpUtil;
import com.icia.common.util.JsonUtil;
import com.icia.common.util.StringUtil;
import com.icia.manager.model.Response;
import com.icia.manager.model.User;
import com.icia.manager.service.UserService;

@Controller("indexController")
public class IndexController
{
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private UserService userService;
	
	// 쿠키명
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	@RequestMapping(value="/index")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		if(CookieUtil.getCookie(request, AUTH_COOKIE_NAME) != null)
		{
			return "/user/list";
			//return "/user/list";
		}
		else
		{
			return "/index";
		}
	}
	
	@RequestMapping(value="/loginProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> loginProc(HttpServletRequest request, HttpServletResponse response)
	{
		String userId = HttpUtil.get(request, "userId");
		String userPwd = HttpUtil.get(request, "userPwd");
		Response<Object> ajaxResponse = new Response<Object>();
		
		if(!StringUtil.isEmpty(userId) && !StringUtil.isEmpty(userPwd))
		{
			User user = userService.userSelect(userId);
			
			if(user != null)
			{
				if(StringUtil.equals(user.getUserPwd(), userPwd))
					{
						if(StringUtil.equals(user.getAdminStatus(), "Y"))
						{
							String userUID = user.getUserUID();
		 	               	CookieUtil.addCookie(response, "/", -1, AUTH_COOKIE_NAME, CookieUtil.stringToHex(userUID));
		 	                ajaxResponse.setResponse(0, "Success"); // 로그인 성공
						}
						else
						{
							ajaxResponse.setResponse(404, "Not Found1"); // 관리자 정보 없음 (Not Found)
						}
					}
					else
					{
						ajaxResponse.setResponse(-1, "Passwords do not match"); // 비밀번호 불일치
					}
				
			}
			else
			{
				ajaxResponse.setResponse(404, "Not Found2"); // 관리자 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
		
		if(logger.isDebugEnabled())
		{
			logger.debug("[IndexController] /loginProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
		
		return ajaxResponse;
	}
	
	@RequestMapping(value="/loginOut", method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request, HttpServletResponse response)
	{
		if(CookieUtil.getCookie(request, AUTH_COOKIE_NAME) != null)
		{
			CookieUtil.deleteCookie(request, response, "/", AUTH_COOKIE_NAME);
		}
		
		return "redirect:/";
	}
	
	
	//빅데이터 관리
	@RequestMapping(value="/data/view")
	public String view(HttpServletRequest request, HttpServletResponse response)
	{
		
		return "/data/view";
	}
	
}
