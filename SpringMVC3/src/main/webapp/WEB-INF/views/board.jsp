<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!--  == controller -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- 자동완성 기능을 위해 가져온 jquery-ui library -->
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.1/themes/smoothness/jquery-ui.css">

<!-- Chart를 그리기 위해 가져오는 라이브러리 -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2"></script>

</head>
<body>
	<div class="jumbotron">
		<h1>빅데이터 23차 게시판</h1>
		<p>Bootstrap을 사용하여 간단하게 게시판을 만들어보자</p>
	</div>

	<div class="container">
		<div class="card">
			<div class="card-header">게시판 연습</div>
			<div class="card-body">

				<form id="searchForm" onsubmit="return false;" class="form-inline">
					<div align="right" class="form-group">
						<select name="type" class="form-control btn-outline-success"
							id="sel1">
							<option class="btn-light">작성자</option>
							<option class="btn-light">제목</option>
						</select>
					</div>
					<input id="auto_complete" name="text" type="text"
						class="form-control"> <input id="searchBtn" type="submit"
						class="btn btn-success" value="검색">
				</form>
				<br>

				<table id="myTable" class="table table-bordered table-hover">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
					</tr>

					<!-- JSTL/EL 사용해서 request 영역 안에 저장돼 있는 게시글 정보를 전부 화면에 출력 -->
					<!-- var: 사용할 변수명 -->
					<c:forEach items="${list}" var="b">
						<tr>
							<td>${b.idx}</td>
							<td>
								<!-- 1. QueryString으로 데이터 보내기 (?idx=)
                         2. 경로상에 그냥 바로 데이터 포함해서 보내기 (PathVariable)
                     --> <a href="${cpath}/boardContent/${b.idx}">${b.title}</a>
							</td>
							<td>${b.writer}</td>
							<td>${b.indate}</td>
						</tr>
					</c:forEach>
				</table>
				<button onclick="location.href='${cpath}/register'" type="button"
					class="btn btn-success btn-sm">글쓰기</button>
				<button class="btn btn-success btn-sm chartBtn">차트보기</button>
			</div>
			<div class="card_footer myChartArea">
				<h1>차트 그리는 영역</h1>
				<canvas width="500" height="500" id="myChart"></canvas>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var cpath = "${cpath}";
	</script>
	<script>
		// 1. 첫 실행시, myChartArea영역을 숨겨놓기
		$(".myChartArea").hide();
		// 2. 차트보기 버튼을 클릭하면, myChartArea 영역을 보여주기
		// .css()매개변수 하나만 있으면 그 하나만 가져오겠다
		$(".chartBtn").on("click", function(){
			if($(".myChartArea").css("display") == "none"){
				// myChartArea영역이 숨겨져 있다면, 영역을 보여주기
				// .slideDown() 
				// -> 아래 방향으로 서서히 나타나 지면서 보이는 행위를 하는 메서드
				// -> 슬라이드 다운을 할 요소를 처음부터 css 속성인 display 속성을 none으로 설정해야 함
				$(".myChartArea").slideDown();
				// 비동기통신을 사용해서 /artist url로 데이터 요청
				// --> 언급된 빈도수가 가장 높은 상위 5명에 대한
				//     아티스트명과, 빈도수 데이터를 결과값으로 받아와서
				// 	   차트를 그려보기
				
				// 추가설명
				// $.ajax() : 비동기식 ajax를 이용해서 HTTP 요청을 전송함
				$.ajax({
					url : `${cpath}/artist`,  // 클라이언트가 HTTP 요청을 보낼 서버의 URL 주소
					dataType : "json",  // 서버에서 보내줄 데이터의 타입
					success : function(res){
						console.log("받아온 데이터 >> ", res)
						// 라벨, 데이터 생성
						var labels = [];
						var datas = [];
						for(var i = 0; i<res.length; i++){
							labels.push(res[i].artist);
							datas.push(res[i].keywordCnt);
						}
						drawChart(labels, datas);
					}, 
					error: function(e) {
						console.log(e);
					}
					
				})
				
			}else{
				// canvas 삭제하고 
				$("#myChart").remove();
				// 다시 새로운 canvas 추가하기
				$(".myChartArea").append('<canvas width="500" height="500" id="myChart"></canvas>');
				// 그렇지 않은 경우, 영역을 숨겨주기
				$(".myChartArea").slideUp();
			}
			
		})
		
		function drawChart(labels, datas){
			// 1. 차트 그릴 canvas 태그를 선택
			var ctx = $("#myChart");
			const data = {
					  // DB에서 조회한 아티스트명으로 변경
					  labels: labels,
					  datasets: [{
					    label: 'My First Dataset',
					    // DB에서 조회한 키워드 빈도수로 변경
					    data: datas,
					    backgroundColor: [
					      '#f0ead2',
					      '#dde5b6',
					      '#adc178',
					      '#a98467',
					      '#6c584c'
					    ],
					    hoverOffset: 4
					  }]
					};
			const options = {
				// 차트 크기를 상대적인 크기가 아니라, 내가 원하는 크기로 변경
				// display : relative 속성을 풀어주는 방법
				responsive : false,
				// 따로 설치한 플러그인 사용하기
				plugins : {
					datalabels : {
						color : [
							"black",
							"black",
							"white",
							"white",
							"white"
						],
						formatter : function(v, c){
							console.log("f확인>> ", v);
							console.log("c확인>> ", c);
							// c 출력해서 구조 확인해보기!!
							// chart 종류마다 구조가 다름
							return c.chart.data.labels[c.dataIndex]
						},
						font : {
							size : 18
						}
					}
					
				}
			};
			const config = {
					// plugin의 종류가 여러개 있음
					// --> ChartDatalabels 객체를 넘겨주기
					plugins : [ChartDataLabels],
					  // 속성명 : 변수명
					  // 1. 차트 종류 지정
					  type: 'pie',
					  // 2. 들어갈 데이터 지정
					  data: data,
					  options : options 
					};
			// 2. 차트 그리기 
			// new Chart(차트영역, 객체형식으로 된 차트의 옵션들)
			new Chart(ctx, config)
		
		}
	
	
	
	
	</script>
	<script src="resources/js/myfirstjs.js" ></script>







</body>
</html>