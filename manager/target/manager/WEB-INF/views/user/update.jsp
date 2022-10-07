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
  width: 15%;
}
table td{
  width: 35%;
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

function fn_userUpdate()
{
	if(icia.common.isEmpty($("#userPwd").val()))
	{
		alert("비밀번호를 입력하세요.");
		$("#userPwd").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#userName").val()))
	{
		alert("이름을 입력하세요.");
		$("#userName").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#userNick").val()))
	{
		alert("닉네임을 입력하세요.");
		$("#userNick").focus();
		return;
	}
	
	if(icia.common.isEmpty($("#userEmail").val()))
	{
		alert("이메일을 입력하세요.");
		$("#userEmail").focus();
		return;
	}
		
	
	if(!confirm("회원정보를 수정하시겠습니까?"))
	{
		return;
	}
	
	
    var form = $("#regForm")[0];
    var formData = new FormData(form);
    
    $.ajax({
  	  type:"POST",
  	  enctype:'multipart/form-data',
  	  url:"/user/updateProc",
  	  data:formData,
  	  processData:false,
  	  contentType:false,
  	  cache:false,
  	  beforeSend:function(xhr)
  	  {
  		  xhr.setRequestHeader("AJAX", "true");
  	  },
	  success: function (ajaxResponse) 
      {
		  icia.common.log(ajaxResponse);
		  if(ajaxResponse.code == 0)
		  {
			  alert("회원정보가 수정 되었습니다.");
			  fn_colorbox_close(parent.fn_pageInit);
		  }
		  else if(ajaxResponse.code == -1)
		  {
			  alert("회원정보 수정중 오류가 발생하였습니다.");
		  }
		  else if(ajaxResponse.code == 400)
		  {
			  alert("파라미터 오류.");
		  }
		  else if(ajaxResponse.code == 404)
		  {
			  alert("회원정보가 없습니다.");
			  fn_colorbox_close();
		  }
	   },
       complete : function(data) 
	   {
    	   icia.common.log(data);
	   },
	   error : function(xhr, status, error) 
	   {
    	   icia.common.error(error);
	   }
	});
    
}

function fn_validateEmail(value)
{
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	
	return emailReg.test(value);
}

function fn_idPwdCheck(val)
{
	var regex = /^[a-zA-Z0-9]{4,12}$/;

	return regex.test(val);
}
</script>
</head>
<body>
<div class="layerpopup" style="width:1123px; margin:auto; margin-top:5%;">
	<h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">사용자 수정</h1>
	<div class="layer-cont">
		<form name="regForm" id="regForm" method="post">
			<table>
				<tbody>
					<tr>
						<th scope="row">회원고유번호</th>
						<td>
							${user.userUID}
							<input type="hidden" id="userUID" name="userUID" value="${user.userUID}" />
						</td>
						<th scope="row">아이디</th>
						<td>
							${user.userId}
							<input type="hidden" id="userId" name="userId" value="${user.userId}" />
						</td>
					</tr>
					<tr>
						<th scope="row">비밀번호</th>
						<td>
							<input type="text" id="userPwd" name="userPwd" value="${user.userPwd}" style="font-size:1rem;;" maxlength="50" placeholder="비밀번호" />
						</td>
					
						<th scope="row">이름</th>
						<td>
							<input type="text" id="userName" name="userName" value="${user.userName}" style="font-size:1rem;;" maxlength="50" placeholder="이름" />
						</td>
					</tr>
					<tr>
						<th scope="row">닉네임</th>
						<td>
							<input type="text" id="userNick" name="userNick" value="${user.userNick}" style="font-size:1rem;;" maxlength="50" placeholder="닉네임" />
						</td>
						<th scope="row">이메일</th>
						<td>
							<input type="text" id="userEmail" name="userEmail" value="${user.userEmail}" style="font-size:1rem;;" maxlength="50" placeholder="이메일" />
						</td>
					</tr>
					<tr>
						<th scope="row">전화번호</th>
						<td colspan="3">
							${user.userPhone}
							<input type="hidden" id="userPhone" name="userPhone" value="${user.userPhone}" />
						</td>
					</tr>
					<tr>
						<th scope="row">상태</th>
						<td>
							<c:if test="${user.status == 'Y'}">정상&nbsp;</c:if>
	                		<c:if test="${user.status == 'N'}">정지&nbsp;</c:if>
							<select id="status" name="status" style="font-size: 1rem; width: 7rem; height: 2rem;">
								<option value="Y"<c:if test="${user.status == 'Y'}"> selected</c:if>>정상</option>
								<option value="N"<c:if test="${user.status == 'N'}"> selected</c:if>>정지</option>
							</select>
						</td>
						<th scope="row">등록일</th>
						<td>
							${user.regDate}
							<input type="hidden" id="regDate" name="regDate" value="${user.regDate}" />
						</td>
					</tr>
					<c:if test="${!empty user.bizNum && !empty user.bizName}">
					<tr>
					<th scope="row">사업자번호</th>
						<td>
							${user.bizNum}
							<input type="hidden" id="bizNum" name="bizNum" value="${user.bizNum}" />
						</td>
						<th scope="row">대표자명</th>
						<td>
							${user.bizName}
							<input type="hidden" id="bizName" name="bizName" value="${user.bizName}" />
						</td>
					</tr>
					</c:if>
					<c:if test="${!empty user.userFile}">
					<tr>
						<th scope="row">등록프로필</th>
						<td>
							${user.userFile.fileName}
						</td>
						<th scope="row">파일첨부</th>
         				<td><input type="file" id="userFile" name="userFile" class="file-content" placeholder="파일을 선택하세요." required /></td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</form>
		<div class="pop-btn-area" style="float: right;">
			<button onclick="fn_userUpdate()" class="btn-type01"><span>수정</span></button>
			<button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
		</div>
	</div>
</div>
</body>
</html>