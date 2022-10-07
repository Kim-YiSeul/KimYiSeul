<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	// GNB 번호 (빅데이터 관리)
	request.setAttribute("_gnbNo", 8);
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>


</head>
<body>
<%@ include file="/WEB-INF/views/include/admin_gnb.jsp" %>
<div style="text-align: center;">
<img alt="" class="BigData" src="../resources/images/빅데이터 시각화.png" 
	style="width: 1000px; height: 700px;">
</div>
</body>
</html>