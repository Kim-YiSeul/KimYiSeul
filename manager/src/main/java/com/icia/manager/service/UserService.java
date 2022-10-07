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
import com.icia.manager.dao.UserDao;
import com.icia.manager.model.User;
import com.icia.manager.model.UserFile;

@Service("userService")
public class UserService 
{
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	//파일 저장 경로
	@Value("#{env['user.upload.save.dir']}")
	private String USER_UPLOAD_SAVE_DIR;
		
	@Autowired
	private UserDao userDao;
	
	//사용자 리스트
	public List<User> userList(User user)
	{
		List<User> list = null;
		
		try
		{
			list = userDao.userList(user);
		}
		catch(Exception e)
		{
			logger.debug("[UserService] userList Exception", e);
		}
		
		return list;
	}
		
	//사용자 수 조회
	public int userListCount(User user)
	{
		int count = 0;
		
		try
		{
			count = userDao.userListCount(user);
		}
		catch(Exception e)
		{
			logger.debug("[userService] userListCount Exception", e);
		}
		
		return count;
	}
	
	//사용자 조회
	public User userSelect(String userId)
	{
		User user = null;
	      
		try
		{
			user = userDao.userSelect(userId);
		}
		catch(Exception e)
		{
			logger.error("[userService] userSelect Exception", e);
		}
	      
		return user;
	}
	
	//사용자UID 조회
	public User userUIDSelect(String userUID)
	{
		User user = null;
	      
		try
		{
			user = userDao.userUIDSelect(userUID);
		}
		catch(Exception e)
		{
			logger.error("[userService] userUIDSelect Exception", e);
		}
	      
		return user;
	}
	
	//사용자 조회(첨부파일 포함)
	public User userView(String userUID)
	{
		User user = null;
		try
		{
			user = userDao.userUIDSelect(userUID);
			
			if(user != null)
			{
				UserFile userFile = userDao.userFileSelect(userUID);
				
				if(userFile != null)
				{
					user.setUserFile(userFile);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("[UserService] userView Exception", e);
		}
		
		return user;
	}
	
	//수정form조회(첨부파일 포함)
	public User userViewUpdate(String userUID)
	{
		User user = null;
		
		try
		{
			user = userDao.userUIDSelect(userUID);
			
			if(user != null)
			{
				UserFile userFile = userDao.userFileSelect(userUID);
				
				if(userFile != null)
				{
					user.setUserFile(userFile);
				}
			}
		}
		catch(Exception e)
		{
			logger.error("[UserService] userViewUpdate Exception", e);
		}
		
		return user;
	}
   
	//사용자 수정
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public int userUpdate(User user) throws Exception
    {
       int count = userDao.userUpdate(user);
       if(count > 0 && user.getUserFile() != null)
       {
    	   UserFile delUserFile = userDao.userFileSelect(user.getUserUID());
    	   
    	   if(delUserFile != null)
    	   {
    		   FileUtil.deleteFile(USER_UPLOAD_SAVE_DIR + FileUtil.getFileSeparator() + delUserFile.getFileName());  
    		   userDao.userFileDelete(user.getUserUID());
    	   }
    	   user.getUserFile().setUserUID(user.getUserUID());
    	   user.getUserFile().setFileSeq((short)1);
    	   
    	   userDao.userFileInsert(user.getUserFile());
       }
       
       return count;
    }
	
}
