package com.icia.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.manager.model.Board;
import com.icia.manager.model.BoardFile;

@Repository("boardDao")
public interface BoardDao 
{
	//게시물 리스트
	public List<Board> boardList(Board board);	
	
	//총 게시물 수
	public long boardListCount(Board board);
	
	//게시물 조회
	public Board boardSelect(long bbsSeq);
	
	//게시물 수정
	public int boardUpdate(Board board);

	//첨부파일 조회
	public BoardFile boardFileSelect(long bbsSeq);

	//첨부파일 삭제
	public int boardFileDelete(long bbsSeq);
	
	//게시물 첨부파일 등록
	public int boardFileInsert(BoardFile boardFile);
	
	//게시물 리스트
	public List<Board> helpList(Board board);	
	
	//help 총 게시물 수
	public long helpListCount(Board board);
}