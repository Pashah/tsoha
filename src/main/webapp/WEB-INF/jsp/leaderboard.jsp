<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" >
        <title>Leaderboard</title>
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
        <h1>Leaderboard: ${leaderboard.name}</h1>
        <br/>
        <h2>Ranking:</h2>
        <br/>
        <ol>
        <c:forEach var="user" items="${users}">
            
                <li>Käyttäjänimi: ${user.username}
                    Pisteet: ${user.points}</li>
            
            <br/>
        </c:forEach>
        </ol>
            </div>
        </div>
    </body>
</html>
