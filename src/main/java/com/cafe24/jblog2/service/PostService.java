package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.PostDao;
import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.PostVo;

@Service
public class PostService {

	@Autowired
	PostDao postDao;

	public Boolean postWrite(PostVo postVo) {
		return postDao.postWrite(postVo);
	}

	public PostVo getPostInfo(PostVo postVo) {
		return postDao.getPostInfo(postVo);
	}

	public List<PostVo> getCategoryPost(Integer category_no) {
		return postDao.getCategoryPost(category_no);
	}

	public Boolean deleteByCategory(int category_no) {
		return postDao.deleteByCategory(category_no);
	}
	
}
