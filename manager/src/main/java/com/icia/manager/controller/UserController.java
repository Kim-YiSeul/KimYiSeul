package com.icia.manager.controller;

import java.util.List;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.icia.common.util.FileData;
import com.icia.common.util.HttpUtil;
import com.icia.common.util.JsonUtil;
import com.icia.common.util.StringUtil;
import com.icia.manager.model.Paging;
import com.icia.manager.model.Response;
import com.icia.manager.model.User;
import com.icia.manager.model.UserFile;
import com.icia.manager.service.UserService;

@Controller("userController")
public class UserController 
{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//파일 저장 경로
	@Value("#{env['user.upload.save.dir']}")
	private String USER_UPLOAD_SAVE_DIR;
	
	@Autowired
	private UserService userService;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 5;
	
	@RequestMapping(value="/user/list")
	public String list(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		//상태(Y:정상, N:정지)
		String status = HttpUtil.get(request, "status");
		//검색타입(1:회원아이디, 2:회원명)
		String searchType = HttpUtil.get(request, "searchType");
		//검색값
		String searchValue = HttpUtil.get(request, "searchValue");
		//현재 페이지
		long curPage = HttpUtil.get(request, "curPage", 1);
		//총 회원 수
		long totalCount = 0;
		//페이징 객체
		Paging paging = null;
		//회원리스트
		List<User> list = null;
		//조회객체
		User user = new User();
		//상태 (Y:사용, N:정지)
		user.setStatus(status);
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				user.setUserId(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				user.setUserName(searchValue);
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
		
		totalCount = userService.userListCount(user);
		
		if(totalCount > 0)
		{
			paging = new Paging("/user/list", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
			
			paging.addParam("status", status);
	        paging.addParam("searchType", searchType);
	        paging.addParam("searchValue", searchValue);
	        paging.addParam("curPage", curPage);
	        
	        user.setStartRow(paging.getStartRow());
	        user.setEndRow(paging.getEndRow());
	        
	        list = userService.userList(user);
		}
		
	    model.addAttribute("list", list);
	    model.addAttribute("searchType", searchType);
	    model.addAttribute("searchValue", searchValue);
	    model.addAttribute("status", status);
        model.addAttribute("curPage", curPage);
	    model.addAttribute("paging", paging);
			
		return "/user/list";
	}
	
	//회원 조회 수정
	@RequestMapping(value="/user/update")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		//회원고유번호
		String userUID = HttpUtil.get(request, "userUID");

		if(!StringUtil.isEmpty(userUID)) 
		{
			User user = userService.userViewUpdate(userUID);
         
			if(user != null)
			{
				model.addAttribute("user", user);
			}
		}

		return "/user/update";
	}
	
	//회원정보 수정
	@RequestMapping(value="/user/updateProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateProc(MultipartHttpServletRequest request, HttpServletResponse response)
	{
		//회원고유번호
		String userUID = HttpUtil.get(request, "userUID");
		//사용자 아이디
		String userId = HttpUtil.get(request, "userId");
		//비밀번호
		String userPwd = HttpUtil.get(request, "userPwd");
		//사용자이름
		String userName = HttpUtil.get(request, "userName");
		//사용자 닉네임
		String userNick = HttpUtil.get(request, "userNick");
		//사용자 이메일 
		String userEmail = HttpUtil.get(request, "userEmail");
		//전화번호
		String userPhone = HttpUtil.get(request, "userPhone");
		//상태 (Y:사용, N:정지)
		String status = HttpUtil.get(request, "status");
		//등록일
		String regDate = HttpUtil.get(request, "regDate");
		//사업자번호
		String bizNum = HttpUtil.get(request, "bizNum");
		//사업자명
		String bizName = HttpUtil.get(request, "bizName");
		//파일데이터 객체
		FileData fileData = HttpUtil.getFile(request, "userFile", USER_UPLOAD_SAVE_DIR);
      
		Response<Object> ajaxResponse = new Response<Object>();

		if(!StringUtil.isEmpty(userUID))
		{
			User user = userService.userUIDSelect(userUID);
			
			if(user != null)
			{
				user.setUserPwd(userPwd);
				user.setUserId(userId);
				user.setUserName(userName);
				user.setUserNick(userNick);
				user.setUserEmail(userEmail);
				user.setUserPhone(userPhone);
				user.setStatus(status);
				user.setRegDate(regDate);
				user.setBizNum(bizNum);
				user.setBizName(bizName);
				
				//첨부파일 여부
				if(fileData != null && fileData.getFileSize() > 0)
				{
					UserFile userFile = new UserFile();
					userFile.setFileName(fileData.getFileName());
					userFile.setFileOrgName(fileData.getFileOrgName());
				    userFile.setFileExt(fileData.getFileExt());
				    userFile.setFileSize(fileData.getFileSize());    
				    
				    user.setUserFile(userFile);
				}   
				
				try
				{
				     if(userService.userUpdate(user) > 0)
				     {
				    	 ajaxResponse.setResponse(0, "success");
				     }
				     else
				     {
				    	 ajaxResponse.setResponse(-1, "fail"); // 오류
				     }
				}
				catch(Exception e)
				{
					logger.error("[UserController] updateProc Exception", e);
					ajaxResponse.setResponse(500, "internal server error");
				}
			}
			else
			{
				ajaxResponse.setResponse(404, "Not Found"); // 사용자 정보 없음 (Not Found)
			}
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
      
		if(logger.isDebugEnabled())
		{
			logger.debug("[UserController] /user/updateProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}
      
		return ajaxResponse;
	}

}
