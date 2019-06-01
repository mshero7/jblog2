package com.cafe24.jblog2.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public Boolean join(BlogVo blogVo) {
		int num = sqlSession.insert("blog.join", blogVo);
		
		return num == 1;
	}

	public BlogVo getBlogInfo(String userId) {
		BlogVo result = sqlSession.selectOne("blog.getBlogInfo", userId);
		return result;
	}

	public Boolean blogProfileChange(BlogVo blogProfileVo) {
		int result = sqlSession.update("blog.profileChange",blogProfileVo);
		return 1 == result;
	}

}
