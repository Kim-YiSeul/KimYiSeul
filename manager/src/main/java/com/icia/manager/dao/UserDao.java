package com.icia.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icia.manager.model.User;
import com.icia.manager.model.UserFile;

@Repository("userDao")
public interface UserDao 
{
	//사용자 리스트
	public List<User> userList(User user);
	
	//사용자 수 조회
	public int userListCount(User user);
	
	//사용자 조회
	public User userSelect(String userId);
	
	//사용자UID 조회
	public User userUIDSelect(String userUID);
	
	//사용자 수정
	public int userUpdate(User user);
	
	//첨부파일 조회
	public UserFile userFileSelect(String userUID);
	
	//첨부파일 등록
	public int userFileInsert(UserFile userFile);
	
	//첨부파일 삭제
	public int userFileDelete(String userUID);

}