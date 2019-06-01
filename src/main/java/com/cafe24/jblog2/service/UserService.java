package com.cafe24.jblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.BlogDao;
import com.cafe24.jblog2.repository.CategoryDao;
import com.cafe24.jblog2.repository.UserDao;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.CategoryVo;
import com.cafe24.jblog2.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public Boolean join(UserVo userVo) {
		BlogVo blogVo = new BlogVo();
		CategoryVo categoryVo = new CategoryVo();
		
		userDao.join(userVo);
		
		blogVo.setBlog_id(userVo.getId());
		blogVo.setTitle(userVo.getName()+"의 블로그");
		blogVo.setLogo("/images/20194297470616.ico");
		blogDao.join(blogVo);
		
		categoryVo.setBlog_id(userVo.getId());
		categoryVo.setName("(분류되지 않은 게시물들)");
		categoryVo.setDescription("분류하지 않은 게시물들");
		categoryDao.join(categoryVo);
		
		return true;
	}

	public UserVo login(UserVo userVo) {
		return userDao.login(userVo);
	}
	
	public Boolean existId(String id) {
		return userDao.existId(id);
	}

}