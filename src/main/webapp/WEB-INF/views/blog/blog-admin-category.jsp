<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script> 
	$(docsument).ready( function(){
	    $("#category_delete").click( function() {
	        if(confirm("카테고리를 삭제하면 하위 글까지 모두 삭제됩니다. 그래도 삭제하시겠습니까?")) {
	            $(this).parent().click();
	        } else {
	            return false;
	        }
	    });
	});
</script>
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
				
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<c:set var="count" value="${fn:length(categoryList) }"/>
					<c:forEach items="${categoryList }" var="categoryList" varStatus="status">
					
					<c:choose>
						<c:when test="${categoryList.name  eq '(분류되지 않은 게시물들)'}">
							<tr>
								<td>${ status.index + 1 }</td>
								<td>${categoryList.name }</td>
								<td>1</td> <!-- 수정해야함 -->
								<td>${categoryList.description }</td>
								<td></td>	
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td>${ status.index + 1 }</td>
								<td>${categoryList.name }</td>
								<td>1</td> <!-- 수정해야함 -->
								<td>${categoryList.description }</td>
								<td><a id="category_delete" href="${pageContext.request.contextPath}/${authUser.id }/admin/category/delete/${categoryList.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					</c:forEach>					  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form action="${pageContext.servletContext.contextPath }/${authUser.id}/admin/category/add" method="POST">
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
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