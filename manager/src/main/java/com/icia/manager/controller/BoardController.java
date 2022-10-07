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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.icia.common.util.StringUtil;
import com.icia.common.util.FileData;
import com.icia.common.util.HttpUtil;
import com.icia.common.util.JsonUtil;
import com.icia.manager.controller.BoardController;
import com.icia.manager.service.BoardService;
import com.icia.manager.model.BoardFile;
import com.icia.manager.model.Board;
import com.icia.manager.model.Paging;
import com.icia.manager.model.Response;

@Controller("boardController")
public class BoardController 
{
	private static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//쿠키명 지정
	@Value("#{env['auth.cookie.name']}")
	private String AUTH_COOKIE_NAME;
	
	//파일 저장 경로
	@Value("#{env['board.upload.save.dir']}")
	private String BOARD_UPLOAD_SAVE_DIR;
	
	@Autowired
	private BoardService boardService;
	
	private static final int LIST_COUNT = 10;
	private static final int PAGE_COUNT = 5;
	
	//게시판 리스트
	@RequestMapping(value="/board/list")
	public String list(ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		//조회 객체
		Board board = new Board();
		//조회항목(1:작성자, 2:제목, 3:내용)
		String searchType = HttpUtil.get(request, "searchType");
		//조회값
		String searchValue = HttpUtil.get(request, "searchValue", "");
		//현재페이지
		long curPage = HttpUtil.get(request, "curPage", (long)1);
		//총 게시물 수
		long totalCount = 0;
		//게시물 리스트
		List<Board> list = null;
		//페이징 객체
		Paging paging = null;
		//게시판 번호
		board.setBbsNo(5);
		//게시물 상태(N:게시물 활성화, Y:게시물 삭제)
  		String status = HttpUtil.get(request, "status");
  		//상태
  		board.setStatus(status);
		
		if(!StringUtil.isEmpty(searchType) && !StringUtil.isEmpty(searchValue))
		{
			if(StringUtil.equals(searchType, "1"))
			{
				board.setUserNick(searchValue);
			}
			else if(StringUtil.equals(searchType, "2"))
			{
				board.setBbsTitle(searchValue);
			}
			else if(StringUtil.equals(searchType, "3"))
			{
				board.setBbsTitle(searchValue);
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

		totalCount = boardService.boardListCount(board);
		
		if(totalCount > 0)
		{
			paging = new Paging("/board/list", totalCount, LIST_COUNT, PAGE_COUNT, curPage, "curPage");
			
			paging.addParam("bbsNo", board.getBbsNo());
			paging.addParam("searchType", searchType);
			paging.addParam("searchValue", searchValue);
			paging.addParam("curPage", curPage);
			paging.addParam("status", status);
			
			board.setStartRow(paging.getStartRow());
			board.setEndRow(paging.getEndRow());
			
			list = boardService.boardList(board);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", searchValue);
		model.addAttribute("curPage", curPage);
		model.addAttribute("paging", paging);
		model.addAttribute("bbsNo", board.getBbsNo());
	    model.addAttribute("status", status);
		
		return "/board/list";
	}
	
	//게시판 조회 수정
	@RequestMapping(value="/board/update")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response)
	{
		//게시물 번호
  		long bbsSeq = HttpUtil.get(request, "bbsSeq", (long)0);
  		//조회항목(1:작성자, 2:제목, 3:내용)	
  		String searchType = HttpUtil.get(request, "searchType", "");
  		//조회값
  		String searchValue = HttpUtil.get(request, "searchValue", "");
  		//현재페이지
  		long curPage = HttpUtil.get(request, "curPage", (long)1);
		//게시물 상태(N:게시물 활성화, Y:게시물 삭제)
  		String status = HttpUtil.get(request, "status");

		if(bbsSeq > 0) 
		{
			Board board = boardService.boardViewUpdate(bbsSeq);
			
			if(board != null)
			{
				model.addAttribute("board", board);
			}
		}
		
		model.addAttribute("searchType", searchType);
  		model.addAttribute("searchValue", searchValue);
  		model.addAttribute("curPage", curPage);
	    model.addAttribute("status", status);
  		
		return "/board/update";
	}
	
	//게시판정보 수정
	@RequestMapping(value="/board/updateProc", method=RequestMethod.POST)
	@ResponseBody
	public Response<Object> updateProc(MultipartHttpServletRequest request, HttpServletResponse response)
	{
		//회원고유번호
		String userUID = HttpUtil.get(request, "userUID");
		//게시물고유번호
  		long bbsSeq = HttpUtil.get(request, "bbsSeq", (long)0);
  		//사용자 닉네임
		String userNick = HttpUtil.get(request, "userNick");
		//게시물 조회수
		int bbsReadCnt = HttpUtil.get(request, "bbsReadCnt", (int)0);
		//게시물 제목
  		String bbsTitle = HttpUtil.get(request, "bbsTitle");
  		//게시물 내용
  		String bbsContent = HttpUtil.get(request, "bbsContent");
  		//게시물 등록일
  		String regDate = HttpUtil.get(request, "regDate");
  		//파일데이터 객체
  		FileData fileData = HttpUtil.getFile(request, "bbsFile", BOARD_UPLOAD_SAVE_DIR);
  		//게시물 상태(N:게시물 활성화, Y:게시물 삭제)
  		String status = HttpUtil.get(request, "status");
      
		Response<Object> ajaxResponse = new Response<Object>();
		if(bbsSeq > 0)
		{			
			Board board = boardService.boardSelect(bbsSeq);
			
			if(board != null)
			{
				board.setUserUID(userUID);
				board.setBbsSeq(bbsSeq);
				board.setUserNick(userNick);
				board.setBbsReadCnt(bbsReadCnt);
				board.setBbsTitle(bbsTitle);
				board.setBbsContent(bbsContent);
				board.setRegDate(regDate);
				board.setStatus(status);
				
				//첨부파일 여부
				if(fileData != null && fileData.getFileSize() > 0)
				{	
					BoardFile boardFile = new BoardFile();
					boardFile.setFileName(fileData.getFileName());
					boardFile.setFileOrgName(fileData.getFileOrgName());
					boardFile.setFileExt(fileData.getFileExt());
					boardFile.setFileSize(fileData.getFileSize());
					
					board.setBoardFile(boardFile);
				}
				
				try
				{
					if(boardService.boardUpdate(board) > 0)
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
					logger.error("[BoardController] updateProc Exception", e);
					ajaxResponse.setResponse(500, "Internal server error");
				}
			}
			else
			{
				ajaxResponse.setResponse(404, "Not Found"); // 게시물 정보 없음 (Not Found)
			}
	  		
		}
		else
		{
			ajaxResponse.setResponse(400, "Bad Request"); // 파라미터가 올바르지 않음 (Bad Request)
		}
      
		if(logger.isDebugEnabled())
		{
			logger.debug("[BoardController] /board/updateProc response\n" + JsonUtil.toJsonPretty(ajaxResponse));
		}

		return ajaxResponse;
	}

}
