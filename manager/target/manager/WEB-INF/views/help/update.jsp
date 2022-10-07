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
input[type=text], input[type=password], input[type=file]{
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

function fn_helpUpdate()
{
	if(icia.common.isEmpty($("#bbsContent").val()))
	{
		alert("내용 입력하세요.");
		$("#bbsContent").focus();
		return;
	}
	
	if(!confirm("공지사항 내용을 수정하시겠습니까?"))
	{
		return;
	}
	
	var form = $("#updateForm")[0];
    var formData = new FormData(form);
    
    $.ajax({
    	  type:"POST",
    	  enctype:'multipart/form-data',
    	  url:"/help/updateProc",
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
				  alert("공지사항 게시물이 수정 되었습니다.");
				  fn_colorbox_close(parent.fn_pageInit);
			  }
			  else if(ajaxResponse.code == -1)
			  {
				  alert("공지사항 게시물 수정중 오류가 발생하였습니다.");
			  }
			  else if(ajaxResponse.code == 400)
			  {
				  alert("파라미터 오류.");
			  }
			  else if(ajaxResponse.code == 404)
			  {
				  alert("공지사항 게시물이 없습니다.");
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

</script>
</head>
<body>
<div class="layerpopup" style="width:1123px; margin:auto; margin-top:5%;">
	<h1 style="font-size: 1.6rem; margin-top: 3rem; margin-bottom: 1.6rem; padding: .5rem 0 .5rem 1rem; background-color: #e0e4fe;">공지사항 수정</h1>
	<div class="layer-cont">
		<form name="updateForm" id="updateForm" method="post">
			<table>
				<tbody>
					<tr>
						<th scope="row">게시판 분류</th>
						<td>
							<c:if test="${board.bbsNo == '1'}">공지사항</c:if>
					    	<c:if test="${board.bbsNo == '2'}">FAQ</c:if>
					    	<c:if test="${board.bbsNo == '3'}">개인회원</c:if>
					    	<c:if test="${board.bbsNo == '4'}">기업회원</c:if>
							<input type="hidden" id="bbsNo" name="bbsNo" value="${board.bbsNo}" />
						</td>
						<th scope="row">게시물번호</th>
						<td>
							${board.bbsSeq}
							<input type="hidden" id="bbsSeq" name="bbsSeq" value="${board.bbsSeq}" />
						</td>
					</tr>
					<tr>
						<th scope="row">닉네임</th>
						<td>
							${board.userNick}
							<input type="hidden" id="userNick" name="userNick" value="${board.userNick}" />
						</td>
						<th scope="row">등록일</th>
						<td>
							${board.regDate}
							<input type="hidden" id="regDate" name="regDate" value="${board.regDate}" />
						</td>
					</tr>
					<tr>
						<th scope="row">제목</th>
						<td colspan="3">
							<input type="text" id="bbsTitle" name="bbsTitle" value="${board.bbsTitle}" style="font-size:1rem;;" maxlength="30" placeholder="제목" />
						</td>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td colspan="3">
							<textarea class="summernote" id="bbsContent" name="bbsContent" placeholder="내용을 입력해주세요.">${board.bbsContent}</textarea> 
				            <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
				            <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
				            <script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
				            <script src="/resources/summernote/summernote-lite.js"></script>
				            <script src="/resources/summernote/lang/summernote-ko-KR.js"></script>
				            <link rel="stylesheet" href="/resources/summernote/summernote-lite.css">
				            <script>$('.summernote').summernote({
						            	//에디터 높이
						                height: 300,
						                maxHeight: 300,
						                //에디터 한글 설정
						                lang: "ko-KR",
						                callbacks: {
						                      onInit: function (c) {
						                          c.editable.html('${board.bbsContent}');
						                      }
						                },
						                toolbar: [
						                     //글꼴 설정
						                     ['fontname', ['fontname']],
						                     //글자 크기 설정
						                     ['fontsize', ['fontsize']],
						                     //굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
						                     ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
						                     //글자색
						                     ['color', ['forecolor','color']],
						                     //표만들기
						                     ['table', ['table']],
						                     //글머리 기호, 번호매기기, 문단정렬
						                     ['para', ['ul', 'ol', 'paragraph']],
						                     //줄간격
						                     ['height', ['height']],
						                     //그림첨부, 링크만들기, 동영상첨부
						                     ['insert',['picture','link','video']],
						                     //코드보기, 확대해서보기, 도움말
						                     ['view', ['codeview','fullscreen', 'help']]
						                 ],
						                 //추가한 글꼴
						                 fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
						                 //추가한 폰트사이즈
						                 fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
						                 
						              });
				            </script>
						</td>
					</tr>
					<tr>
						<th scope="row">조회수</th>
						<td>
							<fmt:formatNumber type="number" maxFractionDigits="3" value="${board.bbsReadCnt}"/>
							<input type="hidden" id="bbsReadCnt" name="bbsReadCnt" value="${board.bbsReadCnt}" />
						</td>
						<th scope="row">등록일</th>
						<td>
							${board.regDate}
							<input type="hidden" id="regDate" name="regDate" value="${board.regDate}" />
						</td>
					</tr>
					
					<c:if test="${!empty board.bbsTitle}">
					<c:if test="${!empty board.boardFile}">
					<tr>
						<th scope="row">등록파일</th>
						<td>
							${board.boardFile.fileOrgName}
						</td>
						<th scope="row">파일첨부</th>
         				<td><input type="file" id="bbsFile" name="bbsFile" class="file-content" placeholder="파일을 선택하세요." required /></td>
					</tr>
					</c:if>
					</c:if>
					<tr>
						<th scope="row">게시물 상태</th>
						<td>
							<c:if test="${board.status == 'Y'}">게시물 삭제 &nbsp;</c:if>
							<c:if test="${board.status == 'N'}">게시물 활성화 &nbsp;</c:if>
							<select id="status" name="status" style="font-size: 1rem; width: 9rem; height: 2rem;">
								<option value="Y"<c:if test="${board.status == 'Y'}"> selected</c:if>>게시물 삭제</option>
								<option value="N"<c:if test="${board.status == 'N'}"> selected</c:if>>게시물 활성화</option>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="pop-btn-area" style="float: right;">
			<button onclick="fn_helpUpdate()" class="btn-type01"><span>수정</span></button>
			<button onclick="fn_colorbox_close()" id="colorboxClose" class="btn-type01" style="margin-left: 1rem;"><span>닫기</span></button>
		</div>
	</div>
</div>
</body>
</html>