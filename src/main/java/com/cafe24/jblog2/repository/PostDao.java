package com.cafe24.jblog2.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.BlogVo;
import com.cafe24.jblog2.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	SqlSession sqlSession;

	public Boolean postWrite(PostVo postVo) {
		int result = sqlSession.insert("post.postWrite",postVo);
		return 1 == result;
	}

	public PostVo getPostInfo(PostVo postVo) {
		PostVo result = sqlSession.selectOne("post.postInfo",postVo);
		return result;
	}

	public List<PostVo> getCategoryPost(Integer category_no) {
		List<PostVo> result = sqlSession.selectList("post.categoryPost",category_no);
		return result;
	}

	public Boolean deleteByCategory(int category_no) {
		int result = sqlSession.delete("post.deleteByCategory",category_no);
		return result == 1;
	}

}
