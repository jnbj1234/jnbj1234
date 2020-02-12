<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
 
 	<script type="text/javascript">
 		//아이디 중복 체크버튼 클릭시 호출 되는 함수 
 		function winopen() {
			//id를 입력 했는지 체크
			//입력한 ID값을 얻어...빈공백인지 파악
			if(document.fr.id.value == ""){//아이디를 입력 하지 않았다면...
				alert("아이디를 입력 하세요.");
				//아이디 입력란에 포커스 깜빡
				document.fr.id.focus();
				return;
			}
			
			//새창 열때... 우리가 입력한 ID를 전달 할수 있도록
			var fid = document.fr.id.value;
			window.open("join_IDCheck.jsp?userid=" + fid,"","width=400,height=200");
			
		}
 	
 	</script>
 
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문메인이미지 -->
		<div id="sub_img_member"></div>
		<!-- 본문메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Join us</a></li>
				<li><a href="#">Privacy policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 본문내용 -->
		<article>
			<h1>Join Us</h1>
			<!-- DB에 새로 추가할 회원 내용을 입력하고 .. 회원가입 전송 버튼 클릭시..
				 입력한 모든 내용을 request내장객체 메모리영역에 저장 하여 유지 된 상태로
				 joinPro.jsp를 요청 함.
			 -->
			<form action="joinPro.jsp" id="join" method="post" name="fr">
				<fieldset>
					<legend>Basic Info</legend>
					<label>User ID</label> <input type="text" name="id" class="id">
					<input type="button" value="ID중복체크" class="dup" onclick="winopen();"><br>
					<label>Password</label> <input type="password" name="passwd"><br>
					<label>Retype Password</label> <input type="password" name="passwd2"><br>
					<label>Name</label> <input type="text" name="name"><br>
					<label>E-Mail</label> <input type="email" name="email"><br>
					<label>Retype E-Mail</label> <input type="email" name="email2"><br>
				</fieldset>

				<fieldset>
					<legend>Optional</legend>
					<label>Address</label> <input type="text" name="address"><br>
					<label>Number</label> <input type="text" name="tel"><br>
					<label>Mobile Phone Number</label> <input type="text" name="mtel"><br>
				</fieldset>
				<div class="clear"></div>
				<div id="buttons">
					<input type="submit" value="회원가입" class="submit"> 
					<input type="reset" value="Cancel" class="cancel">
				</div>
			</form>
		</article>
		<!-- 본문내용 -->
		<!-- 본문들어가는 곳 -->

		<div class="clear"></div>
		<!-- 푸터들어가는 곳 -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>