package com.cafe24.jblog2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog2.repository.CategoryDao;
import com.cafe24.jblog2.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	public List<CategoryVo> getCategoryList(String blog_id) {
		return categoryDao.getCategoryList(blog_id);
	}

	public Boolean addCategory(CategoryVo categoryVo) {
		return categoryDao.addCategory(categoryVo);
	}

	public Boolean deleteCategory(int category_no) {
		return categoryDao.deleteCategory(category_no);
	}

}
