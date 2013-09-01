<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" >
        <title>Tervetuloa Liikunta Leaderboardiin</title>
    </head>
    <body>
        <div id="wrap">
            <div id="left">
                <div id="top">
                    <c:if test="${userId == null}">
                        <a href=${pageContext.request.contextPath}/app/register>Rekisteröidy</a>
                        <a href=${pageContext.request.contextPath}/app/login>Kirjaudu sisään</a>
                    </c:if>
                    <c:if test="${userId != null}">
                        <a href=${pageContext.request.contextPath}/app/logout>Kirjaudu ulos</a>
                    </c:if>
                </div>
                <ul>
                    <li><a href=${pageContext.request.contextPath}/app/mainpage>Pääsivu</a></li>
                    <c:if test="${userId != null}">
                        <li><a href=${pageContext.request.contextPath}/app/viewUserPage>Käyttäjäsivut</a></li>
                        <li><a href=${pageContext.request.contextPath}/app/editLeaderboards>Leaderboardien hallinta</a></li>
                        <li><a href=${pageContext.request.contextPath}/app/accomplishmentspage>Suoritusten hallinta</a></li>
                    </c:if>
                </ul>
            </div>
            <div id="center">
            <h1>Tervetuloa leaderboardiin</h1>
            <br/>
        <c:if test="${userId == null}">
            <p>Kirjaudu sisään tai rekisteröidy niin pääset käyttämään eri ominaisuuksia</p>
        </c:if>   
            

       <br/>
       <h3>Leaderboardit:</h3>
       <c:forEach var="leaderboard" items="${leaderboards}">
          <ul>
                <li>
                    <a href=${pageContext.request.contextPath}/app/viewLeaderboard?leaderboardId=${leaderboard.id}>${leaderboard.name}</a>
                </li>
          </ul>
      </c:forEach>
        </div>
     </div>           
    </body>
</html>
