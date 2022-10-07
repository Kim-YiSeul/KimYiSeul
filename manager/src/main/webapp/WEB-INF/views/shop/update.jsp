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
  margin-bottom: 3rem;
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

function fn_shopUpdate()
{
	if(icia.common.isEmpty($("#shopName").val()))
	{
		alert("매장이름을 입력하세요.");
		$("#shopName").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#shopType").val()))
	{
		alert("매장종류를 선택하세요.");
		$("#shopType").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#shopAddress").val()))
	{
		alert("매장위치를 입력하세요.");
		$("#shopAddress").focus();
		return;
	}
	
	if(!confirm("매장정보를 수정하시겠습니까?"))
	{
		return;
	}
	
	var formData = {
		shopUID: $("#shopUID").val(),
		userUID: $("#userUID").val(),	
		shopName: $("#shopName").val(),
		shopType: $("#shopType").val(),
		bizNum: $("#bizNum").val(),
		bizName: $("#bizName").val(),
		shopHoliday: $("#shopHoliday").val(),
		shopHashtag: $("#shopHashtag").val(),
		shopLocation1: $("#shopLocation1").val(),
		shopLocation2: $("#shopLocation2").val(),
		shopAddress: $("#shopAddress").val(),
		shopTelephone: $("#shopTelephone").val(),
		regDate: $("#regDate").val(),
		shopIntro: $("#shopIntro").val(),
		shopContent: $("#shopContent").val()
	};
	
	icia.ajax.post({
		
		url: "/shop/updateProc",
		data: formData,
		success: function (ajaxResponse) 
        {
			icia.common.log(ajaxResponse);
			
			if(ajaxResponse.code == 0)
			{
				alert("매장정보가 수정 되었습니다.");
				fn_colorbox_close(parent.fn_pageInit);
			}
			else if(ajaxResponse.code == -1)
			{
				alert("매장정보 수정중 오류가 발생하였습니다.");
			}
			else if(ajaxResponse.code == 400)
			{
				alert("파라미터 오류.");
			}
			else if(ajaxResponse.code == 404)
			{
				alert("매장정보가 없습니다.");
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
	<h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">매장 수정</h1>
	<div class="layer-cont">
		<form name="shopForm" id="shopForm" method="post">
			<table>
				<tbody>
					<tr>
						<th scope="row">매장번호</th>
						<td>
							${shop.shopUID}
							<input type="hidden" id="shopUID" name="shopUID" value="${shop.shopUID}" />
						</td>
						<th scope="row">관리자번호</th>
						<td>
							<a href="../user/update?userUID=${shop.userUID}" name="userUpdate">${shop.userUID}</a>
							<input type="hidden" id="userUID" name="userUID" value="${shop.userUID}" />
						</td>
					</tr>
					<tr>
						<th scope="row">매장이름</th>
						<td>
							${shop.shopName}
							<input type="text" id="shopName" name="shopName" value="${shop.shopName}" style="font-size:1rem;;" maxlength="50" placeholder="매장이름" />
						</td>
					
						<th scope="row">매장타입</th>
						<td>
							<select id="shopType" name="shopType" style="font-size: 1rem; width: 7rem; height: 2rem;">
								<option value="1"<c:if test="${shop.shopType == '1'}"> selected</c:if>>파인다이닝</option>
								<option value="2"<c:if test="${shop.shopType == '2'}"> selected</c:if>>오마카세</option>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row">사업자번호</th>
						<td>
							${shop.bizNum}
							<input type="hidden" id="bizNum" name="bizNum" value="${shop.bizNum}" />
						</td>
						<th scope="row">대표자명</th>
						<td>
							${shop.bizName}
							<input type="text" id="bizName" name="bizName" value="${shop.bizName}" />
						</td>
					</tr>
					<tr>
						<th scope="row">매장휴일</th>
						<td>
						<c:forTokens items="${shop.shopHoliday}" delims="," var="shopHoliday">
							<c:if test="${shopHoliday == '0'}">일</c:if>
							<c:if test="${shopHoliday == '1'}">월</c:if>
							<c:if test="${shopHoliday == '2'}">화</c:if>
							<c:if test="${shopHoliday == '3'}">수</c:if>
							<c:if test="${shopHoliday == '4'}">목</c:if>
							<c:if test="${shopHoliday == '5'}">금</c:if>
							<c:if test="${shopHoliday == '6'}">토</c:if>
							<c:if test="${shopHoliday == '-1'}">휴무없음</c:if>
						</c:forTokens>
							<input type="text" id="shopHoliday" name="shopHoliday" value="${shop.shopHoliday}" style="font-size:1rem;;" maxlength="50" placeholder="0:일, 1:월, 2:화 , 3:수, 4:목, 5:금, 6:토, -1:휴무없음" />
						</td>
						<th scope="row">해시태그</th>
						<td>
							${shop.shopHashtag}
							<input type="text" id="shopHashtag" name="shopHashtag" value="${shop.shopHashtag}" style="font-size:1rem;;" maxlength="50" placeholder="해시태그" />
						</td>
					</tr>
					<tr>
						<th colspan="4" scope="row">매장위치</th>
					</tr>
					<tr>
						<td colspan="4">
							${shop.shopLocation1} ${shop.shopLocation2} ${shop.shopAddress}
							<input type="text" id="shopLocation1" name="shopLocation1" value="${shop.shopLocation1}" style="font-size:1rem;;" maxlength="50" placeholder="도로명주소" />
							<input type="text" id="shopLocation2" name="shopLocation2" value="${shop.shopLocation2}" style="font-size:1rem;;" maxlength="50" placeholder="지번주소" />
							<input type="text" id="shopAddress" name="shopAddress" value="${shop.shopAddress}" style="font-size:1rem;;" maxlength="50" placeholder="상세주소" />
						</td>
					</tr>
					<tr>
						<th scope="row">전화번호</th>
						<td>
							${shop.shopTelephone}
							<input type="hidden" id="shopTelephone" name="shopTelephone" value="${shop.shopTelephone}" />
						</td>
						<th scope="row">매장등록일</th>
						<td>
							${shop.regDate}
							<input type="hidden" id="regDate" name="regDate" value="${shop.regDate}" />
						</td>
					</tr>
					<tr>
						<th scope="row" style="width:15%">메뉴이름/가격</th>
						<td colspan="3">
							<c:forEach items="${shop.shopMenu}" var="shopMenu" varStatus="status">
				    			${shopMenu.shopMenuName}&nbsp;&nbsp;<fmt:formatNumber type="number" maxFractionDigits="3" value="${shopMenu.shopMenuPrice}"/>원 </br>
				    		</c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row">한줄소개</th>
						<td colspan="3">
							${shop.shopIntro}
							<input type="text" id="shopIntro" name="shopIntro" value="${shop.shopIntro}" style="font-size:1rem;;" maxlength="50" placeholder="한줄소개" />
						</td>
					</tr>
					<tr>
						<th scope="row">매장소개</th>
						<td colspan="3">
							${shop.shopContent}
							<input type="text" id="shopContent" name="shopContent" value="${shop.shopContent}" style="font-size:1rem;;" maxlength="50" placeholder="매장소개" />
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="pop-btn-area" style="float: right;">
			<button onclick="fn_shopUpdate()" class="btn-type01"><span>수정</span></button>
			<button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
		</div>
	</div>
</div>
</body>
</html>