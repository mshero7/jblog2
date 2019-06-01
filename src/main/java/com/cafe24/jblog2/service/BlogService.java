package com.cafe24.jblog2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.BlogDao;
import com.cafe24.jblog2.repository.UserDao;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	public Boolean join(BlogVo blogVo) {
		return blogDao.join(blogVo);
	}

	public BlogVo getBlogInfo(String userId) {
		return blogDao.getBlogInfo(userId);
	}

	public Boolean blogProfileChange(BlogVo blogProfileVo) {
		return blogDao.blogProfileChange(blogProfileVo);
	}

}
