package com.cafe24.jblog2.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.service.CategoryService;
import com.cafe24.jblog2.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	SqlSession sqlSession;

	public Boolean join(CategoryVo categoryVo) {
		int num = sqlSession.insert("category.join", categoryVo);
		
		return num == 1;
	}

	public List<CategoryVo> getCategoryList(String blog_id) {
		List<CategoryVo> list = sqlSession.selectList("category.getCategoryList",blog_id);
		return list;
	}

	public Boolean addCategory(CategoryVo categoryVo) {
		int num = sqlSession.insert("category.addCategory",categoryVo);
		return num == 1;
	}

	public Boolean deleteCategory(int category_no) {
		int num = sqlSession.delete("category.deleteCategory",category_no);
		return num == 1;
	}
	
	
}
