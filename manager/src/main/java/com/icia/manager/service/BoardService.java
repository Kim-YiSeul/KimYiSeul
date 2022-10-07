package com.icia.manager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.common.util.FileUtil;
import com.icia.manager.dao.BoardDao;
import com.icia.manager.model.Board;
import com.icia.manager.model.BoardFile;

@Service("boardService")
public class BoardService 
{
	private static Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	//파일 저장 경로
	@Value("#{env['board.upload.save.dir']}")
	private String BOARD_UPLOAD_SAVE_DIR;
	
	@Autowired
	private BoardDao boardDao;
	
	//게시물 리스트
	public List<Board> boardList(Board board)
	{
		List<Board> list = null;
		
		try
		{
			list = boardDao.boardList(board);
		}
		catch(Exception e)
		{
			logger.error("[BoardService] boardList Exception", e);
		}
		
		return list;
	}
	
	//총 게시물 수
	public long boardListCount(Board board)
	{
		long count = 0;
		
		try
		{
			count = boardDao.boardListCount(board);
		}
		catch(Exception e)
		{
			logger.error("[BoardService] boardListCount Exception", e);
		}
		
		return count;
	}
	
	//게시물 조회
	public Board boardSelect(long bbsSeq)
	{
		Board board = null;
		
		try
		{
			board = boardDao.boardSelect(bbsSeq);
		}
		catch(Exception e)
		{
			logger.error("[BoardService] boardSelect Exception", e);
		}
		return board;
	}
	
	//게시물 조회(첨부파일 포함)
	public Board boardView(long bbsSeq)
	{
		Board board = null;
		try
		{
			board = boardDao.boardSelect(bbsSeq);
			
			if(board != null)
			{				
				//첨부파일 조회
				BoardFile boardFile = boardDao.boardFileSelect(bbsSeq);
				
				if(boardFile != null)
				{
					board.setBoardFile(boardFile);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("[BoardService] boardView Exception", e);
		}
		
		return board;
	}
	
	//게시물 수정form 조회(첨부파일 포함)
	public Board boardViewUpdate(long bbsSeq)
	{
		Board board = null;
		
		try
		{
			board = boardDao.boardSelect(bbsSeq);
			
			if(board != null)
			{
				//첨부파일 조회
				BoardFile boardFile = boardDao.boardFileSelect(bbsSeq);
				
				if(boardFile != null)
				{
					board.setBoardFile(boardFile);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("[BoardService] boardViewUpdate Exception", e);
		}
		
		return board;
	}
	
	//게시물 수정
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int boardUpdate(Board board) throws Exception
	{
		int count = boardDao.boardUpdate(board);	
		
		if(count > 0 && board.getBoardFile() != null)
		{
			BoardFile delBoardFile = boardDao.boardFileSelect(board.getBbsSeq());	
			
			//기존 첨부파일 있으면 삭제
			if(delBoardFile != null && board.getBoardFile().getFileSize() > 0)
			{
				FileUtil.deleteFile(BOARD_UPLOAD_SAVE_DIR + FileUtil.getFileSeparator() + delBoardFile.getFileName());
				boardDao.boardFileDelete(board.getBbsSeq());
			}

			//새로운 첨부파일 등록
			if(board.getBoardFile().getFileSize() > 0)
			{
			board.getBoardFile().setBbsSeq(board.getBbsSeq());
			board.getBoardFile().setFileSeq((short)1);
			boardDao.boardFileInsert(board.getBoardFile());
			}
			
		}	
		
		return count;
	}
	
	//help 게시물 리스트
	public List<Board> helpList(Board board)
	{
		List<Board> helpList = null;
		
		try
		{
			helpList = boardDao.helpList(board);
		}
		catch(Exception e)
		{
			logger.error("[BoardService] helpList Exception", e);
		}
		
		return helpList;
	}
		
	//help 총 게시물 수
	public long helpListCount(Board board)
	{
		long count = 0;
		
		try
		{
			count = boardDao.helpListCount(board);
		}
		catch(Exception e)
		{
			logger.error("[BoardService] helpListCount Exception", e);
		}
		
		return count;
	}
}
