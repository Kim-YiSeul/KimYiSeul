<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style>
html, body{
  color:  #525252;
}
table{
  width:100%;
  border: 1px solid #c4c2c2;
}
table th, td{
  border-right: 1px solid #c4c2c2;
  border-bottom: 1px solid #c4c2c2;
  height: 3rem;
  padding-left: .5rem;
  padding-right: 1rem;
}
table th{
  background-color: #e0e4fe;
  width: 12%;
}
table td{
  width: 38%;
}
input[type=text], input[type=password]{
  height:2rem;
  width: 100%;
  border-radius: .2rem;
  border: .2px solid rgb(204,204,204);
  background-color: rgb(246,246,246);
}
button{
  width: 5rem;
  margin-top: 1rem;
  border: .1rem solid rgb(204,204,204);
  border-radius: .2rem;
  box-shadow: 1px 1px #666;
}
button:active {
  background-color: rgb(186,186,186);
  box-shadow: 0 0 1px 1px #666;
  transform: translateY(1px);
}
</style>
<script type="text/javascript" src="/resources/js/colorBox.js"></script>
<script type="text/javascript">
$(document).ready(function() 
{
	//$("#schName").focus();
});

function fn_orderUpdate()
{
	if(icia.common.isEmpty($("#orderStatus").val()))
	{
		alert("예약상태를 입력하세요.");
		$("#orderStatus").focus();
		return;
	}
	
	if(!confirm("예약정보를 수정하시겠습니까?"))
	{
		return;
	}
	
	var formData = {
		orderUID: $("#orderUID").val(),
		shopUID: $("#shopUID").val(),	
		shopName: $("#shopName").val(),
		userUID: $("#userUID").val(),
		userName: $("#userName").val(),
		orderMenuName: $("#orderMenuName").val(),
		orderMenuPrice: $("#orderMenuPrice").val(),
		orderMenuQuantity: $("#orderMenuQuantity").val(),
		reservationPeople: $("#reservationPeople").val(),
		orderStatus: $("#orderStatus").val(),
		payType: $("#payType").val(),
		totalAmount: $("#totalAmount").val(),
		paymentKey: $("#paymentKey").val(),
		regDate: $("#regDate").val()
	};
	
	icia.ajax.post({
		
		url: "/order/updateProc",
		data: formData,
		success: function (ajaxResponse) 
        {
			icia.common.log(ajaxResponse);
			
			if(ajaxResponse.code == 0)
			{
				alert("예약정보가 수정 되었습니다.");
				fn_colorbox_close(parent.fn_pageInit);
			}
			else if(ajaxResponse.code == -1)
			{
				alert("예약정보 수정중 오류가 발생하였습니다.");
			}
			else if(ajaxResponse.code == 400)
			{
				alert("파라미터 오류.");
			}
			else if(ajaxResponse.code == 404)
			{
				alert("예약정보가 없습니다.");
				fn_colorbox_close();
			}
        },
        complete : function(data) 
		{
			// 응답이 종료되면 실행, 잘 사용하지않는다
			icia.common.log(data);
		},
		error : function(xhr, status, error) 
		{
			icia.common.error(error);
		}
	});
}

</script>
</head>
<body>
<div class="layerpopup" style="width:1123px; margin:auto; margin-top:5%;">
	<h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">예약내역 수정</h1>
	<div class="layer-cont">
		<form name="orderForm" id="orderForm" method="post">
			<table>
				<tbody>
					<tr>
						<th scope="row" style="width:17%">예약번호</th>
						<td colspan="3">
							${order.orderUID}
							<input type="hidden" id="orderUID" name="orderUID" value="${order.orderUID}" />
						</td>
					</tr>
					<tr>
						<th scope="row">매장번호</th>
						<td>
							${order.shopUID}
							<input type="hidden" id="shopUID" name="shopUID" value="${order.shopUID}" />
						</td>
						<th scope="row">매장이름</th>
						<td>
							${order.shopName}
							<input type="hidden" id="shopName" name="shopName" value="${order.shopName}" />
						</td>
					</tr>
					<tr>
						<th scope="row">예약자번호</th>
						<td>
							${order.userUID}
							<input type="hidden" id="userUID" name="userUID" value="${order.userUID}" />
						</td>
						<th scope="row">예약자명</th>
						<td>
							${order.userName}
							<input type="hidden" id="userName" name="userName" value="${order.userName}" />
						</td>
					</tr>
					<tr>
						<th scope="row">메뉴이름/가격</th>
						<td>
							<c:forEach items="${order.orderMenu}" var="orderMenu" varStatus="status">
				    			${orderMenu.orderMenuName}&nbsp;&nbsp;<fmt:formatNumber type="number" maxFractionDigits="3" value="${orderMenu.orderMenuPrice}"/>원 </br>
				    		</c:forEach>
						</td>
						<th scope="row">예약인원</th>
						<td>
							${order.reservationPeople}
							<input type="hidden" id="reservationPeople" name="reservationPeople" value="${order.reservationPeople}" />
						</td>
					</tr>
					<tr>
						<th scope="row">메뉴수량</th>
						<td>
							<c:forEach items="${order.orderMenu}" var="orderMenu" varStatus="status">
				    			${orderMenu.orderMenuName}&nbsp;&nbsp;${orderMenu.orderMenuQuantity}개 </br>
				    		</c:forEach>
						</td>
						<th scope="row">총액</th>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${order.totalAmount}"/>
							<input type="hidden" id="totalAmount" name="totalAmount" value="${order.totalAmount}" />
						</td>
					</tr>
					<tr>
						<th scope="row">결제타입</th>
						<td>
							${order.payType}
							<input type="hidden" id="payType" name="payType" value="${order.payType}" />
						</td>
						<th scope="row">예약일</th>
						<td>
							${order.rDate}
							<input type="hidden" id="regDate" name="regDate" value="${order.rDate}" />
						</td>
					</tr>
					<tr>
						<th scope="row">결제상태</th>
						<td colspan="3">
							<c:if test="${order.orderStatus == 'R'}">예약&nbsp;</c:if>
	                		<c:if test="${order.orderStatus == 'E'}">예약만료&nbsp;</c:if>
	                		<c:if test="${order.orderStatus == 'C'}">예약취소&nbsp;</c:if>
	                		<c:if test="${order.orderStatus == 'X'}">당일/하루전 예약취소&nbsp;</c:if>
							<select id="orderStatus" name="orderStatus" style="font-size: 1rem; width: 11rem; height: 2rem;">
								<option value="R"<c:if test="${order.orderStatus == 'R'}"> selected</c:if>>예약</option>
								<option value="E"<c:if test="${order.orderStatus == 'E'}"> selected</c:if>>예약만료</option>
								<option value="C"<c:if test="${order.orderStatus == 'C'}"> selected</c:if>>예약취소</option>
								<option value="X"<c:if test="${order.orderStatus == 'X'}"> selected</c:if>>당일/하루전 예약취소</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">토스번호</th>
						<td>
							${order.paymentKey}
							<input type="hidden" id="paymentKey" name="paymentKey" value="${order.paymentKey}" />
						</td>
						
					</tr>
				</tbody>
			</table>
		</form>
		<div class="pop-btn-area" style="float: right;">
			<button onclick="fn_orderUpdate()" class="btn-type01"><span>수정</span></button>
			<button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
		</div>
	</div>
</div>
</body>
</html>