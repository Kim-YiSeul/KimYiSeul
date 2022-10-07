<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html>

<head>
<link href="/resources/css/style.css" rel="stylesheet">
<script src="https://kit.fontawesome.com/842f2be68c.js" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/resources/js/icia.common.js"></script>
<style>
	.pwds{
		width: 250px;
		height: 30px;
}
</style>
<script type="text/javascript">

$(document).ready(function() {
	
$("#tel_btn").on("click", function() {
	let check = /^[0-9]+$/; 
	if (!check.test($("#tel").val())) {
		alert('숫자만 입력 가능합니다.')
		return;
	}
	else{
	const telNum = $("#tel").val();
	$.ajax({
		type:'POST',
		url:"/user/sendSms2",
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{tel:telNum},
		success : function(response){
			if(response.code == 0){
				alert('인증 번호를 전송했습니다.')
				const code_btn = document.getElementById("code_btn")
				code_btn.disabled = false
				$("#code").focus();
			} 
			else if(response.code == 100){
				alert('계정정보와 일치하지 않는 번호입니다.')
				 $("#tel").focus();
			} 
			else {
				alert('오류가 발생하였습니다.')
				$("#tel").focus();
			}
		}
	})
	}
})	

$("#code_btn").on("click", function(){
	const code = $("#code").val();
	$.ajax({
		type:'POST',
		url:"/user/sendSmsOk",
		header:{"Content-Type":"application/json"},
		dateType:'json',
		data:{code:code},
		success : function(response){
			if(response.code == 0){
				alert('인증되었습니다')
				const btnUpdate = document.getElementById("btnUpdate")
				btnUpdate.disabled = false
				
			} else {
				alert('인증 번호가 다릅니다.')
				$("#code").focus();
			}
		}
	})
})


$("#btnUpdate").on("click", function() { 
	var idPwCheck = /^[a-zA-Z0-9]{4,12}$/;
	var emptCheck = /\s/g;
	if($.trim($("#userPwd").val()).length <= 0)
  	{
     $("#userPwd").focus();
     return;
  	}
	if($.trim($("#userPwd2").val()).length <= 0)
  	{
     $("#userPwd2").focus();
     return;
  	}
	if (emptCheck.test($("#userPwd2").val())) 
	{
	   alert("비밀번호는 공백을 포함할 수 없습니다.");
	   $("#userPwd2").focus();
	   return;
	}
	   
	if (!idPwCheck.test($("#userPwd2").val())) 
	{
	   alert("비밀번호는 영문 대소문자와 숫자로 4~12자리 입니다.");
	   $("#userPwd2").focus();
	   return;
	}
	   
	if ($("#userPwd").val() != $("#userPwd2").val()) 
	{
	   alert("비밀번호가 일치하지 않습니다.");
	   $("#userPwd2").focus();
	   return;
	}
  	
 	$.ajax({
	type:"POST",
	url:"/myPage/PwdUpdate",
	data:{
		userPwd:$("#userPwd2").val()
	},
	dataType:"JSON",
	beforeSend:function(xhr){
		xhr.setRequestHeader("AJAX", "true");
	},
	success:function(response){
		if(response.code == 0)
		{
			alert("비밀번호가 변경되었습니다.");
			opener.parent.updateSuccess();
			self.close();
		}
		else if(response.code == 400)
		{
			opener.parent.ParmError();
			self.close();
		}
		else if(response.code == 404)
		{
			opener.parent.userError();
			self.close();
		}
		else if(response.code == 500)
		{
			opener.parent.updateError();					
			self.close();
		}
		else
		{
			opener.parent.updateError();
			self.close();
		}
	},
	error:function(xhr, status, error)
	{
		icia.common.error(error);
	}
  });
});

	
});
</script>
</head>

<body style="background-color:white; text-align: center;">
<p style="font-family:Cafe24Dangdanghae; color:#d4af7a; margin-top:10px; font-size:20px;">비밀번호 변경</p>
	<form>
		<div>
			<input type="tel" id="tel" name="tel" class="pwds" placeholder="전화번호(- 빼고 작성해주세요)" pattern="[0-9]{11}" required>
			<input type="button" id="tel_btn" value="인증번호 전송" />
		</div>
		<br />
		<div style="margin-right:28px">
			<input type="text" name="code" id="code" class="pwds" placeholder="전송받은 번호" pattern="[0-9]{6}" required>
			<input type="button" id="code_btn" value="번호 확인" disabled />			
		</div >
		<br />
		<div style="margin-right:108px">
		<input type="password" id="userPwd" name="userPwd" class="pwds" placeholder="변경하실 비밀번호를 입력해주세요." maxlength="12">
		</div>
		<br />
		<div style="margin-right:60px">
		<input type="password" id="userPwd2" name="userPwd2" class="pwds" placeholder="다시 한번 입력해주세요." maxlength="12">
		<input type="button" id="btnUpdate" value="확인" disabled />
		</div>
		<br /> 
		<input type="button" value="닫 기" onclick="self.close();" />
	</form>
</body>

</html>