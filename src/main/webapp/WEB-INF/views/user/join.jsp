<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script	src="${pageContext.servletContext.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
	var form_check = false;
	
	$(function() {
		$('#id').change(function() {
			$('#btn-check-id').show();
			$('#img-check-id').hide();
			form_check = false;
		});
		
		$('#btn-check-id').click(function() {
			var id = $('#id').val();

			console.log(id);
			if (id == '') {
				return;
			}

			/* Ajax 통신!*/
			$.ajax({
				url : "/jblog2/user/api/checkId?id=" + id,
				type : "GET",
				dataType : "json",
				data : "",
				success : function(response) {
					if (response.result != "success") {
						console.error(response.message);
						return;
					}

					if (response.data == false) {
						$('#btn-check-id').hide();
						$('#img-check-id').show();
						form_check=true;
					}

					if (response.data == true) {
						alert('이미 존재하는 아이디입니다.');
						$('#id').focus();
						$('#id').val("");

						return;
					}
				},
				error : function(xhr, error) {
					// info debug log error인지 설정가능
					console.error("error " + error);
				}
			});
		});
	});
</script>

</head>

<body>
	<div class="center-content">
		<c:import url='/WEB-INF/views/includes/header.jsp' />
		
		<form class="join-form" action="${pageContext.servletContext.contextPath}/user/join" method="POST">
					
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="id" name="id" type="text"> 
			<input id="btn-check-id" type="button" value="id 중복체크">
			<img id="img-check-id" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y" >
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
