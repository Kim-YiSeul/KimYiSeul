<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

<style>
	html, body{
		width: 100%;
	}
	h2, .h2 {
	    font-size: 2rem;
	}
	.navbar-expand-lg {
	    flex-wrap: nowrap;
	    justify-content: flex-start;
	}
	.navbar-expand-lg .navbar-collapse {
	    display: flex !important;
	    flex-basis: auto;
	}
	.navbar-expand-lg .navbar-nav {
	    flex-direction: row;
	}
	.navbar-expand-lg .navbar-nav .nav-link {
	    padding-right: 1.5rem;
	    padding-left: 1.5rem;
	}
</style>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 1}"> active</c:if>" href="/user/list">회원관리
					    <span class="visually-hidden">(current)</span>
					</a>
					
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 2}"> active</c:if>" href="/shop/list">매장관리
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 3}"> active</c:if>" href="/order/list">예약내역관리
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 4}"> active</c:if>" href="/board/list">게시판관리
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 5}"> active</c:if>" href="/report/list">게시물 신고관리
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 6}"> active</c:if>" href="/help/list">공지사항관리
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 7}"> active</c:if>" href="/entry/list">입점문의관리
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link<c:if test="${_gnbNo == 8}"> active</c:if>" href="/data/view">빅데이터시각화
					    <span class="visually-hidden">(current)</span>
					</a>
				</li>

			</ul>
			<span style="color:white; text-decoration:none; padding-right: 2rem;">${gnbUserNick}</span><a style="color:white; text-decoration:none; padding-right: 1.5rem;" href="/loginOut">로그아웃</a>
		</div>
	</div>
</nav>