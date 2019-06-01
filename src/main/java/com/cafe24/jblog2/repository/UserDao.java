package com.cafe24.jblog2.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public UserDao() {
		System.out.println("UserDao Constructor!!");
	}
	
	public Boolean join(UserVo userVo) {
		int num =  sqlSession.insert("user.join",userVo);
		
		return 1 == num;
	}

	public UserVo login(UserVo userVo) {
		UserVo result = sqlSession.selectOne("user.login",userVo); 
		System.out.println("UserDAO에서의 result 값 : "+result);
		
		return result;
	}

	public UserVo getUserVo(String id) {
		UserVo result = sqlSession.selectOne("user.getUserVo",id);
		return result;
	}
	
	public Boolean existId(String id) {
		Map<String, String> map = new HashMap<String,String>();
		map.put("id", id);
		
		UserVo userVo = sqlSession.selectOne("user.existId", map);
		
		return userVo != null;
	}

}
