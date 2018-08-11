<%@ page language="java" import="java.util.*" import="vo.*" import= "servlet.Document" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<!--
	Helios by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Search</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	</head>
	<body class="homepage">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header">

					<!-- Inner -->
						<div class="inner">
							<header>
						
								<form action = "" name="form" method = "post"  >
								<h2 style="color:white">Clustering and Searching Application</h2>
								<br>

								<div style="width: 420px; margin: 0 auto;" >

									<input type="text" class="form-control" id="query" aria-describedby="Help" placeholder="Enter" style=" float: left;display: inline; width: 380px;" name="query">
									
										<br>
										<br>
									<br>
									<br>

								</div>
							
								<footer>
								<a href="#banner" class="button circled scrolly" onclick = "submitQuery()">Search</a>
								</footer>

							</form>

								<script type="text/javascript">  
							       function submitQuery() {  
							        var th = document.form;        
							        th.action="<%=path%>/SearchServlet";  
							        th.submit();  
							  
							    }  
								</script>
								
							</header>
							
						</div>


				</div>

			<!-- Banner -->
			
			<%Map<String, List<Document>> getClusterNews = (HashMap)session.getAttribute("cluster"); %>
			   
 			<%pageContext.setAttribute("map", getClusterNews);%>
 			
 			<table>
 			 <c:forEach var="entry"  items="${pageScope.map}"> <tr>
 			 <td>
 			 
 			 <b><c:out value="${entry.key}"/></b>
 			 <br>
 			 News:  <c:forEach items="${entry.value}" var="item" varStatus="loop"> <br><a href = "${item.getUrl()}">${item.getTitle()}</a>${!loop.last ? ', ' : ''}
        </c:forEach><br>
        </td>
        </tr>
        <br>
        </c:forEach>
 			
 		
 			


			 	
				<%-- <%for(Map.Entry<String, List<Document>> entry : getClusterNews.entrySet()) { %>
					<%= entry.getKey()%>
					<%for(int i = 0; i < entry.getValue().size(); i++){ %>
						<br>
						<li>
					    <a href ="<%=entry.getValue().get(i).getUrl()%>"><%=entry.getValue().get(i).getTitle()%></a>
						</li>
			<%} %>
			 <% } %> --%>
			 
			<%--  <% Map<String, List<Document>> getClusterNewss = (HashMap)session.getAttribute("cluster");%>
			
				<%for(Map.Entry<String, List<Document>> entry : getClusterNewss.entrySet()) { %>
					<% String arr[] = entry.getKey().split(" ", 2); %>
					<%=arr[0]%>
					<br>
					<%="[Description]: " + entry.getKey()%>
					<%for(int i = 0; i < entry.getValue().size(); i++) { %>
						<br>
						<li>
					    <a href ="<%=entry.getValue().get(i).getUrl()%>"><%=entry.getValue().get(i).getTitle()%></a>
						</li>
			<%} %>
				
			 <% } %> --%>
			
		
			<%-- <%Map<String, List<String[]>> map = (HashMap)session.getAttribute("cluster"); %>
			
			<% for(Map.Entry<String, List<String[]>> entry : map.entrySet()) { %>
			
			<%=entry.getKey()%>
			<%for(int i = 0; i < 2;i++){ %>
			<br>
		    <li>
			<a href ="<%=entry.getValue().get(i)[0] %>"><%=entry.getValue().get(i)[1] %></a>
			</li>
			<%} %>
				
			 <% } %> --%>
				<%-- <section id="banner">
				
							<br>
						<center><h3><p id="uname" value="" style="text-align:center"></p></h3></center>
				  
					</header>
				</section>

			<!-- Carousel -->
				<section class="carousel">
					<div class="reel">

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg2" alt="" /></a>
							<header>
								<h3><p id="mname3" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore4" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg5" alt="" /></a>
							<header>
								<h3><p id="mname6" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore7" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg8" alt="" /></a>
							<header>
								<h3><p id="mname9" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore10" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>
						<article>
							<a href="#" class="image featured"><img src="" id = "mimg11" alt="" /></a>
							<header>
								<h3><p id="mname12" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore13" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg14" alt="" /></a>
							<header>
								<h3><p id="mname15" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore16" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg17" alt="" /></a>
							<header>
								<h3><p id="mname18" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore19" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg20" alt="" /></a>
							<header>
								<h3><p id="mname21" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore22" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg23" alt="" /></a>
							<header>
								<h3><p id="mname24" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore25" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>
						<article>
							<a href="#" class="image featured"><img src="" id = "mimg26" alt="" /></a>
							<header>
								<h3><p id="mname27" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore28" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

						<article>
							<a href="#" class="image featured"><img src="" id = "mimg29" alt="" /></a>
							<header>
								<h3><p id="mname30" value="" style="text-align:center"></p></h3>
								<h4><p id="mscore31" value="" style="text-align:center"></p></h4>
								
							</header>
							
						</article>

					</div>
				</section> --%>

			<!-- Main -->
				

				
				</div>

			<!-- Features -->
				
			<!-- Footer -->
				
					
					

							<!-- Tweets -->
								
								

								</section>

						</div>
						<hr />
						
					</div>
				</div>

		</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/jquery.scrolly.min.js"></script>
			<script src="assets/js/jquery.onvisible.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>