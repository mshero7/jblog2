<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<div id="header">
		<c:choose>
			<c:when test="${empty authUser }">
				<h1><a href="${pageContext.servletContext.contextPath }/${blogVo.blog_id}">메인</a>${blogVo.title}</h1>
					<ul>
						<li><a href="${pageContext.servletContext.contextPath }/">메인</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
					</ul>
				</c:when>
			<c:otherwise>
				<h1>${blogVo.title}</h1>
					<ul>
						<li><a>${authUser.name}님 반갑습니다!</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/">메인</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">블로그 관리</a></li>
					</ul>
			</c:otherwise>
		</c:choose>
		
	
</div>