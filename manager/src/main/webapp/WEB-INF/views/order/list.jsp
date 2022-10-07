<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	// GNB 번호 (예약내역관리)
	request.setAttribute("_gnbNo", 3);
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
*, ::after, ::before {
	box-sizing: unset;
}
.table-hover th, td{
	border: 1px solid #c4c2c2;
	text-align: center;
	vertical-align:middle;
}
.table-hover th, td{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	word-break: break-all;
}
</style>
<script type="text/javascript">
$("document").ready(function(){
	
	$("a[name='orderUpdate']").colorbox({
		iframe:true, 
		innerWidth:1235,
		innerHeight:650,
		scrolling:true,
		onComplete:function()
		{
			$("#colorbox").css("width", "1235px");
			$("#colorbox").css("height", "650px");
			$("#colorbox").css("border-radius", "10px");
		}	
	});
	
	$("a[name='userUpdate']").colorbox({
		iframe:true, 
		innerWidth:1235,
		innerHeight:600,
		scrolling:false,
		onComplete:function()
		{
			$("#colorbox").css("width", "1235px");
			$("#colorbox").css("height", "550px");
			$("#colorbox").css("border-radius", "10px");
		}		
	});
	
	$("a[name='shopUpdate']").colorbox({
		iframe:true, 
		innerWidth:1235,
		innerHeight:700,
		scrolling:true,
		onComplete:function()
		{
			$("#colorbox").css("width", "1235px");
			$("#colorbox").css("height", "700px");
			$("#colorbox").css("border-radius", "10px");
		}
	});
});

function fn_search()
{
	document.searchForm.curPage.value = "1";
	document.searchForm.action = "/order/list";
	document.searchForm.submit();
}

function fn_paging(curPage)
{
	document.searchForm.curPage.value = curPage;
	document.searchForm.action = "/order/list";
	document.searchForm.submit();
}

function fn_pageInit()
{
	$("#searchType option:eq(0)").prop("selected", true);
	$("#searchValue").val("");
	
	fn_search();		
}
</script>
</head>
<body id="school_list">
<%@ include file="/WEB-INF/views/include/admin_gnb.jsp" %>
	<div id="school_list" style="width:90%; margin:auto; margin-top:5rem;">
		<div class="mnb" style="display:flex; margin-bottom:0.8rem;">
			<h2 style="margin-right:auto; color: #525252;">예약내역 리스트</h2>
			<form class="d-flex" name="searchForm" id="searchForm" method="post" style="place-content: flex-end;">
				<select id="orderStatus" name="orderStatus" style="font-size: 1rem; width: 11rem; height: 3rem;">
					<option value="">결제상태</option>
					<option value="R"<c:if test="${orderStatus == 'R'}"> selected</c:if>>예약</option>
					<option value="E"<c:if test="${orderStatus == 'E'}"> selected</c:if>>예약만료</option>
					<option value="C"<c:if test="${orderStatus == 'C'}"> selected</c:if>>예약취소</option>
					<option value="X"<c:if test="${orderStatus == 'X'}"> selected</c:if>>당일/하루전 예약취소</option>
				</select>
				<select id="searchType" name="searchType" style="font-size: 1rem; width: 8rem; height: 3rem; margin-left:.5rem; ">
					<option value="">검색항목</option>
		            <option value="1" <c:if test="${searchType eq '1'}">selected</c:if>>매장이름</option>
		            <option value="2" <c:if test="${searchType eq '2'}">selected</c:if>>예약자명</option>
		            <option value="3" <c:if test="${searchType eq '3'}">selected</c:if>>예약번호</option>
		        </select>
				<input name="searchValue" id="searchValue" class="form-control me-sm-2" style="width:15rem; margin-left:.5rem;" type="text" value="${searchValue}">
				<a class="btn my-2 my-sm-0" href="javascript:void(0)" onclick="fn_search()" style="width:7rem; margin-left:.1rem; background-color: rgb(239, 239, 239); border-color:rgb(118, 118, 118);">조회</a>
				<input type="hidden" name="curPage" value="${curPage}" />
			</form>
		</div>
		<div class="school_list_excel">
			<table class="table table-hover" style="border:1px solid #c4c2c2;">
				<thead style="border-bottom: 1px solid #c4c2c2;">
				<tr class="table-thead-main">
					<th style="width:5%">번호</th>
					<th style="width:10%">예약번호</th>
		            <th style="width:10%">매장이름</th>
		            <th style="width:5%">예약자명</th>
		            <th style="width:29%">예약메뉴</th>
		            <th style="width:4%">예약인원</th>
		            <th style="width:5%">결제상태</th>
		            <!-- <th style="width:5%">결제타입</th> -->
		            <th style="width:7%">총액</th>
		            <th style="width:10%">등록일</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${!empty list}">
				<tr>
					<c:forEach items="${list}" var="list" varStatus="status">
				    <td>${list.rNum}</td>
				    <th style="font-size: 15px;"><a href="/order/update?orderUID=${list.orderUID}" name="orderUpdate">${list.orderUID}</a></th>
				    <th><a href="/shop/update?shopUID=${list.shopUID}" name="shopUpdate">${list.shopName}</a></th>
				    <th><a href="/user/update?userUID=${list.userUID}" name="userUpdate">${list.userName}</a></th>
				    <td style="font-size: 15px;">
				    	<c:forEach items="${list.orderMenu}" var="orderMenu" varStatus="status">
				    		${orderMenu.orderMenuName}
				    	</c:forEach>
				    </td>
				    <td>${list.reservationPeople}</td>
	                <td><c:if test="${list.orderStatus == 'R'}">예약</c:if>
	                	<c:if test="${list.orderStatus == 'E'}">예약만료</c:if>
	                	<c:if test="${list.orderStatus == 'C'}">예약취소</c:if>
	                	<c:if test="${list.orderStatus == 'X'}">당일/하루전 예약취소</c:if>
	                </td>
	                <!-- <td>${list.payType}</td> -->
	                <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${list.totalAmount}"/></td>
	                <td>${list.rDate}</td>
				</tr>
				</c:forEach>	
				</c:if>
				<c:if test="${empty list}">
				<tr>
				    <td colspan="10">등록된 예약내역이 없습니다.</td>
				</tr>	
				</c:if>
				</tbody>
			</table>
			<div class="paging-right" style="text-align: center;">
				<!-- 페이징 샘플 시작 -->
				<c:if test="${!empty paging}">
					<!--  이전 블럭 시작 -->
					<c:if test="${paging.prevBlockPage gt 0}">	
						<a href="javascript:void(0)"  class="btn2 btn-primary" onclick="fn_paging(${paging.prevBlockPage})"  title="이전 블럭">&laquo;</a>
					</c:if>
					<!--  이전 블럭 종료 -->
					<span>
					<!-- 페이지 시작 -->
					<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}">
						<c:choose>
							<c:when test="${i ne curPage}">
								<a href="javascript:void(0)" class="btn2 btn-primary" onclick="fn_paging(${i})" style="font-size:14px;">${i}</a>
							</c:when>
							<c:otherwise>
								<h class="btn2 btn-primary" style="font-size:14px; font-weight:bold;">${i}</h>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- 페이지 종료 -->
					</span>
					<!--  다음 블럭 시작 -->
					<c:if test="${paging.nextBlockPage gt 0}">			
						<a href="javascript:void(0)" class="btn2 btn-primary" onclick="fn_paging(${paging.nextBlockPage})" title="다음 블럭">&raquo;</a>
					</c:if>
					<!--  다음 블럭 종료 -->
				</c:if>
				<!-- 페이징 샘플 종료 -->
			</div>
		</div>
		<form name="bbsForm" id="bbsForm" method="post">
		 <input type="hidden" name="searchType" value="${searchType}" />
		 <input type="hidden" name="searchValue" value="${searchValue}" />
		 <input type="hidden" name="orderStatus" value="${orderStatus}" />
		 <input type="hidden" name="curPage" value="${curPage}" />
		 <input type="hidden" name="orderUID" value="${orderUID}" />
		 <input type="hidden" name="shopUID" value="${shopUID}" />
		 <input type="hidden" name="userUID" value="${userUID}" />
		</form>
	</div>
</body>
</html>