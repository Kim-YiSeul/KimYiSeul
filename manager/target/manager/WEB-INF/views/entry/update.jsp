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

function fn_entryUpdate()
{
	if(icia.common.isEmpty($("#shopName").val()))
	{
		alert("매장명을 입력하세요.");
		$("#shopName").focus();
		return;
	}
	if(icia.common.isEmpty($("#userName").val()))
	{
		alert("문의자명을 입력하세요.");
		$("#userName").focus();
		return;
	}
	if(icia.common.isEmpty($("#userPhone").val()))
	{
		alert("문의자 연락처를 입력하세요.");
		$("#userPhone").focus();
		return;
	}
	if(icia.common.isEmpty($("#userEmail").val()))
	{
		alert("문의자 연락이메이를 입력하세요.");
		$("#userEmail").focus();
		return;
	}
	
	if(!confirm("입점문의 정보를 수정하시겠습니까?"))
	{
		return;
	}
	
	var formData = {
		entrySeq: $("#entrySeq").val(),
		shopName: $("#shopName").val(),	
		userName: $("#userName").val(),
		userPhone: $("#userPhone").val(),
		userEmail: $("#userEmail").val(),
		agreement: $("#agreement").val(),
		status: $("#status").val(),
		resultStatus: $("#resultStatus").val(),
		regDate: $("#regDate").val()
	};
	
	icia.ajax.post({
		
		url: "/entry/updateProc",
		data: formData,
		success: function (ajaxResponse) 
        {
			icia.common.log(ajaxResponse);
			
			if(ajaxResponse.code == 0)
			{
				alert("입점문의 정보가 수정 되었습니다.");
				fn_colorbox_close(parent.fn_pageInit);
			}
			else if(ajaxResponse.code == -1)
			{
				alert("입점문의 정보 수정중 오류가 발생하였습니다.");
			}
			else if(ajaxResponse.code == 400)
			{
				alert("파라미터 오류.");
			}
			else if(ajaxResponse.code == 404)
			{
				alert("입점문의 정보가 없습니다.");
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

//이메일 전송
function fn_emailSend(){          
        var email_auth_cd = '';
        var email = $("#userEmail").val()
        console.log(email);
        $.ajax({
			type : "POST",
			url : "/emailAuth",
			data : {email : email},
			success: function(data){
				alert("인증번호가 발송되었습니다.");
				email_auth_cd = data;
			},
			error: function(data){
				alert("메일 발송에 실패했습니다.");
			}
		}); 
}
	

//입점문의 등록
function fn_entryInsert()
{	$("#btnInsert").prop("disabled", true);
	if(!confirm("입점매장을 등록하시겠습니까?"))
	{
		return;
	}
	
	var formData = {
		entrySeq: $("#entrySeq").val()
	};
	
	icia.ajax.post({
		
		url: "/entry/shopInsertProc",
		data: formData,
		success: function (ajaxResponse) 
        {
			icia.common.log(ajaxResponse);
			
			if(ajaxResponse.code == 0)
			{
				fn_emailSend();
				alert("입점매장이 등록 되었습니다.");
				fn_colorbox_close(parent.fn_pageInit);
				
			}
			else if(ajaxResponse.code == -1)
			{
				alert("입점매장이 등록 중 오류가 발생하였습니다.");
			}
			else if(ajaxResponse.code == 400)
			{
				alert("파라미터 오류.");
			}
			else if(ajaxResponse.code == 404)
			{
				alert("입점문의 정보가 없습니다.");
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
	<h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">입점문의 내역 수정</h1>
	<div class="layer-cont">
		<form name="entryForm" id="entryForm" method="post">
			<table>
				<tbody>
					<tr>
						<th scope="row" style="width:14%">문의번호</th>
						<td colspan="3">
							${entry.entrySeq}
							<input type="hidden" id="entrySeq" name="entrySeq" value="${entry.entrySeq}" />
						</td>
					</tr>
					<tr>
						<th scope="row">매장명</th>
						<td>
							${entry.shopName}
							<input type="text" id="shopName" name="shopName" value="${entry.shopName}" style="font-size:1rem;" maxlength="50" placeholder="매장명" />
						</td>
						<th scope="row" style="width:14%">문의자</th>
						<td>
							${entry.userName}
							<input type="text" id="userName" name="userName" value="${entry.userName}" style="font-size:1rem;" maxlength="50" placeholder="문의자" />
						</td>
					</tr>
					<tr>
						<th scope="row">문의자 연락처</th>
						<td>
							${entry.userPhone}
							<input type="text" id="userPhone" name="userPhone" value="${entry.userPhone}" style="font-size:1rem;" maxlength="50" placeholder="문의자 연락처" />
						</td>
						<th scope="row">문의자 이메일</th>
						<td>
							${entry.userEmail}
							<input type="text" id="userEmail" name="userEmail" value="${entry.userEmail}" style="font-size:1rem;" maxlength="50" placeholder="문의자 이메일" />
						</td>
					</tr>
					<tr>
						<th scope="row">약관동의여부</th>
						<td>
							<c:if test="${entry.agreement == 'Y'}">동의</c:if>
	                		<c:if test="${entry.agreement == 'N'}">미동의</c:if>
							<select id="agreement" name="agreement" style="font-size: 1rem; width: 7rem; height: 2rem;">
								<option value="Y"<c:if test="${entry.agreement == 'Y'}"> selected</c:if>>동의</option>
								<option value="N"<c:if test="${entry.agreement == 'N'}"> selected</c:if>>미동의</option>
							</select>
						</td>
						<th scope="row">문의일</th>
						<td>
							${entry.regDate}
							<input type="hidden" id="regDate" name="regDate" value="${entry.regDate}" />
						</td>
					</tr>
					<tr>
						<th scope="row">처리사항</th>
						<td>
							<c:if test="${entry.status == 'I'}">문의</c:if>
	                		<c:if test="${entry.status == 'A'}">접수</c:if>
	                		<c:if test="${entry.status == 'P'}">처리</c:if>
	                		<c:if test="${entry.status == 'C'}">완료</c:if>
							<select id="status" name="status" style="font-size: 1rem; width: 7rem; height: 2rem;">
								<option value="I"<c:if test="${entry.status == 'I'}"> selected</c:if>>문의</option>
								<option value="A"<c:if test="${entry.status == 'A'}"> selected</c:if>>접수</option>
								<option value="P"<c:if test="${entry.status == 'P'}"> selected</c:if>>처리</option>
								<option value="C"<c:if test="${entry.status == 'C'}"> selected</c:if>>완료</option>
							</select>
						</td>
						<th scope="row">승인여부</th>
						<td>
							<c:if test="${entry.resultStatus == 'Y'}">승인</c:if>
	                		<c:if test="${entry.resultStatus == 'N'}">미승인</c:if>
							<select id="resultStatus" name="resultStatus" style="font-size: 1rem; width: 7rem; height: 2rem;">
								<option value="Y"<c:if test="${entry.resultStatus == 'Y'}"> selected</c:if>>승인</option>
								<option value="N"<c:if test="${entry.resultStatus == 'N'}"> selected</c:if>>미승인</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="pop-btn-area" style="float: right;">
			<button onclick="fn_entryInsert()" id="btnInsert" class="btn-type01" style="width: 6rem;"><span>입점등록</span></button>
			<button onclick="fn_entryUpdate()" class="btn-type01" style="margin-left: 1rem;"><span>수정</span></button>
			<button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
		</div>
	</div>
</div>
</body>
</html>