<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="header">
	
		<c:choose>
			<c:when test="${empty authUser }">
				<h1 class="logo">JBlog</h1>
					<img src="${pageContext.servletContext.contextPath }/assets/images/logo.jpg">
					<ul class="menu">
						<li><a href="${pageContext.servletContext.contextPath }/">메인</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
					</ul>
				</c:when>
			<c:otherwise>
				<h1 class="logo">${blogVo.title}</h1>
					<img alt="" src="${pageContext.servletContext.contextPath }/assets/images/logo.jpg">
					<ul class="menu">
						<li><a href="${pageContext.servletContext.contextPath }/">메인</a></li>
						<li>${authUser.name}님 반갑습니다!</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
						<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}">내블로그</a></li>
					</ul>
			</c:otherwise>
		</c:choose>
		
<!-- 	<h1 class="logo">JBlog</h1> -->
<!-- 	<ul class="menu"> -->
<!-- 		<li><a -->
<%-- 			href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li> --%>
<!-- 		<li><a -->
<%-- 			href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li> --%>
<!-- 		<li><a -->
<%-- 			href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li> --%>
<%-- 		<li><a href="${pageContext.servletContext.contextPath }">내블로그</a></li> --%>
<!-- 	</ul> -->
</div>