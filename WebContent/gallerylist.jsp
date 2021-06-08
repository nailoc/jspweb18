<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.hk.jsp.dao.*" %>
<%@ page import="com.hk.jsp.vo.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지갤러리</title>
<link rel="stylesheet" href="css/style_gallery.css"></link>
<style></style>
</head>

<%
	GalleryDao galdao = GalleryDao.getInstance();
	//int total = galdao.getGalleryList();
	List<GalleryVo> result = galdao.getGalleryList();
%>
<body>
	<h3>이미지 갤러리</h3>
	
	<br>
	<div class="wrapper">
		
		<div class="totalcnt">
			<p>전체 : <%=result.size() %> </p>
		</div>
		<div class="gallist">
		<%
			for(int i=0; i<result.size(); i++) {
				GalleryVo temp = result.get(i);
		%>
			<div class="gallery">
				<a href="galleryshow.jsp?no=<%=temp.getNo()%>">
				<img src="images/<%= temp.getFilename1() %>" alt="여름"></a>
				<div class="desc"><%= temp.getSubject() %></div>
			</div>
		<%
			}
		%>
		<!-- 	<div class="gallery">
				<a href=""><img src="images/summer1_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div>
			
			<div class="gallery">
				<a href=""><img src="images/summer2_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div>
			
			<div class="gallery">
				<a href=""><img src="images/summer3_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div>
			
			4개씩
			
			<div class="gallery">
				<a href=""><img src="images/summer4_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div>
			
			<div class="gallery">
				<a href=""><img src="images/summer5_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div>
			
			<div class="gallery">
				<a href=""><img src="images/summer6_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div>
			
			<div class="gallery">
				<a href=""><img src="images/summer7_600x400.jpg" alt="여름"></a>
				<div class="desc">이미지에 대한 설명을 넣어주세요</div>
			</div> -->
		</div>
		
		<div class="pagination_box">
			<div class="pagination">
				<a href="">처음</a>
				<a href="">1</a>
				<a href="">2</a>
				<a href="">3</a>
				<a href="">4</a>
				<a href="">5</a>
				<a href="">6</a>
				<a href="">&raquo;</a>
				<a href="">마지막</a>
			</div>
		</div>
		
		
		
	</div>
	
	
</body>
<script></script>
</html>
