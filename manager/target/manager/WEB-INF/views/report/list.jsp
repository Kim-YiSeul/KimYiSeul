<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
	// GNB 번호 (게시물 신고관리)
	request.setAttribute("_gnbNo", 5);
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
.table-hover th{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	word-break: break-all;
}
</style>
<script type="text/javascript">
$("document").ready(function(){
	
	$("a[name='reportUpdate']").colorbox({
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
});

function fn_search()
{
	document.searchForm.curPage.value = "1";
	document.searchForm.action = "/report/list";
	document.searchForm.submit();
}

function fn_paging(curPage)
{
	document.searchForm.curPage.value = curPage;
	document.searchForm.action = "/report/list";
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
			<h2 style="margin-right:auto; color: #525252;">게시물 신고 리스트</h2>
			<form class="d-flex" name="searchForm" id="searchForm" method="post" style="place-content: flex-end;">
				<select id="searchType" name="searchType" style="font-size: 1rem; width: 8rem; height: 3rem; margin-left:.5rem; ">
					<option value="">검색항목</option>
		            <option value="1" <c:if test="${searchType eq '1'}">selected</c:if>>신고자</option>
		            <option value="2" <c:if test="${searchType eq '2'}">selected</c:if>>글 제목</option>
		            <option value="3" <c:if test="${searchType eq '3'}">selected</c:if>>글 내용</option>
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
					<th style="width:7%">종류</th>
		            <th style="width:17%">제목 및 내용</th>
		            <th style="width:9%">신고자</th>
		            <th style="width:7%; font-size: 1rem;">스팸게시물</th>
		            <th style="width:11%; font-size: 1rem;">성격에 맞지 않는 글</th>
		            <th style="width:8%; font-size: 1rem;">과도한 욕설</th>
		            <th style="width:13%; font-size: 1rem;">분위기를 어지럽히는 글</th>
		            <th style="width:8%; font-size: 1rem;">기타신고사유</th>
		            <th style="width:15%">신고일</th>
				</tr>
				</thead>
				<tbody>
				<c:if test="${!empty list}">
				<c:forEach items="${list}" var="report" varStatus="status">
				<tr>
				    <td>${report.rNum}</td>
				    
				    <c:if test="${!empty report.bbsTitle}"> 
	                <td>게시물</td>
	                </c:if>
	                <c:if test="${empty report.bbsTitle}"> 
	                <td>댓글</td>
	                </c:if>
	                
	                <c:if test="${!empty report.bbsTitle}"> 
	                <th><a href="/board/update?bbsSeq=${report.bbsSeq}" name="reportUpdate">${report.bbsTitle}</a></th>
	                </c:if>
	                <c:if test="${empty report.bbsTitle}">
	                <th><a href="/board/update?bbsSeq=${report.bbsSeq}" name="reportUpdate">${report.bbsContent}</a></th>
	                </c:if>
	                
	                <td><a href="/user/update?userUID=${report.userUID}" name="userUpdate">${report.userNick}</a></td>
	                <td>${report.report1}</td>
	                <td>${report.report2}</td>
	                <td>${report.report3}</td>
	                <td>${report.report4}</td>
	                <td>${report.etcReport}</td>
	                <td>${report.regDate}</td>
				</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty list}">
				<tr>
				    <td colspan="10">등록된 신고 내용이 없습니다.</td>
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
		 <input type="hidden" name="curPage" value="${curPage}" />
		 <input type="hidden" name="userUID" value="${userUID}" />
	</form>
	</div>
</body>
</html>