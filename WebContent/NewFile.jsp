<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Financial News</title>
</head>
<body>
	<form action="search" method="post">
		<h2 >Clustering and Searching Application</h2>
		<p>
			<label for="query">Search</label>
			<input id="query" name="query" value="${fn:escapeXml(param.query)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<!-- <br/>
	<div id="userCreate"><a href="usercreate">Create User</a></div>
	<br/> -->
	
	<h1>Matching Users</h1>
        <table border="1">
            <tr>
                <th>UserName</th>
                <th>NickName</th>
                <th>Bio</th>
                <th>Followers</th>
                <th>Following</th>
                <th>Tags</th>
            </tr>
            <c:forEach items="${users}" var="user" >
                <tr>
                    <td><c:out value="${user.getUserName()}" /></td>
                    <td><c:out value="${user.getNickName()}" /></td>
                    <td><c:out value="${user.getBio()}" /></td>
                    <td><c:out value="${user.getFollowers()}" /></td>
                    <td><c:out value="${user.getFollowing()}" /></td>
                    <td><c:out value="${user.getTags()}" /></td>
                </tr>
            </c:forEach>
       </table>
	
	<h1>Matching Tweets</h1>
        <table border="0">
            <tr>
                <th>Date</th>
                <th>Hour</th>
                <th>User</th>
                <th>TweetContent</th>
                <th>Favs</th>
                <th>RTs</th>
            </tr>
            <c:forEach items="${tweets}" var="tweet" >
                <tr>
                    <td><c:out value="${tweet.getDate()}" /></td>
                    <td><c:out value="${tweet.getHour()}" /></td>
                    <td><c:out value="${tweet.getUserName()}" /></td>
                    <td><c:out value="${tweet.getTweetContent()}" /></td>
                    <td><c:out value="${tweet.getFavs()}" /></td>
                    <td><c:out value="${tweet.getRTs()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
