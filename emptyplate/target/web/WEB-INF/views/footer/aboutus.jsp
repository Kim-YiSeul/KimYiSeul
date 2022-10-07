<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 개행문자 값을 저장한다.
	pageContext.setAttribute("newLine", "\n");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script>

$(document).ready(function() {
  
 }
</script>
</head>
<body>
 <%@ include file="/WEB-INF/views/include/navigation.jsp" %>
 <!-- ======= aboutus Section ======= -->
  <section id="aboutus1" class="aboutus1">
    <div class="container">
        <div class="row">
            <div class="nav nav-pills nav-justified" id="pills-tab" role="tablist">
                <li class="nav-item" >
                  <a href="/footer/aboutUs">
                  	<button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-home" type="button">AboutUS</button>
                  </a>	
                </li>
                <li class="nav-item">
                  <a href="/footer/company">
                   <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" type="button">Company</button>
                  </a> 
                </li>
                <li class="nav-item">
                  <a href="/footer/logoHistory">
                  	<button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-contact" type="button">Logo History</button>
                  </a>	
                </li>                
            </div>
            <div class="tab-content">
                <div class="tab-pane active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab">
                    <div class="content">
                        <h3 class="title">About US</h3>
                        <hr>
                        <p class="info">
                            EmptyPlate는 '파인다이닝/오마카세' 매장 검색 서비스를 시작으로 영감을 주는 유의미한 데이터, 유저 친화적인 경험을 제공하는 예약 플랫폼으로 성장했습니다.
                            <br>
                            EmptyPlate의 점진적인 성장은 우리의 서비스를 이용하는 고객들의 응원과 격려가 있어 가능했습니다. 고객들의 열정에 보답하기 위해 우리는 모두가 의욕 넘치고 즐겁게 사용할 수 있는 환경을 제공하기 위해 끊임없이 연구하고 과감하게 시도합니다.
                        </p>
                        <div class="card-list">
                          <h3 class="title">EmptyPlate만의 장점</h3>
                          <hr>
                          <ul class="info">
                            <li>
                              <div>
                                <strong>빠르고 편리한 서비스</strong>
                                <p>다양한 기능들을 빠르고 편리하게!</p>
                              </div>
                            </li>
                            <li>
                              <div>
                                <strong>쉽고 편리한 사용방법</strong>
                                <p>남녀노소 누구나! 쉽게 다룰 수 있는 편리한 사용성과 직관적인 UI/UX</p>
                              </div>
                            </li>
                            <li>
                              <div>
                                <strong>Feedback</strong>
                                <p> 문의 및 요청사항에 빠른 피드백!</p>
                              </div>
                            </li>
                            <li>
                              <div>
                                <strong>최고의 효율을 위한 완전 자율 근무</strong>
                                <p> 근무 최고의 업무 효율을 낼 수 있는 근무 환경을 본인이 스스로 선택하고 구축하여, 자율적으로 일할 수 있는 환경을 제공합니다.</p>
                              </div>
                            </li>
                          </ul>  

                        </div>
                    </div>
                </div>
                
            </div>
        

        <div class="swiper-pagination"></div>

        <!--메뉴-->
        
        </div>
      </div>

    </div>
  
 
 </section>        
  <%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>

</html>