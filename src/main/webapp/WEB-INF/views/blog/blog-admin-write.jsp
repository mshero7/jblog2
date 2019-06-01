<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
				<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url='/WEB-INF/views/includes/blog-admin-menu.jsp' />

				<form action="${pageContext.servletContext.contextPath}/${authUser.id}/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input type="text" size="60" name="title">
				      			<select id="category" name="category_no">
					      			<c:forEach items="${categoryList }" var="categoryList">
					      				<option value="${categoryList.no}">${categoryList.name}
					      			</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>