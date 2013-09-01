<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" >
        <title>Hallinnoi leaderboardeja</title>
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
        <h1>Hallinnoi leaderboardeja</h1>
        <br/>
        <c:if test="${userId != null}">
            <h3>Lisää leaderboard</h3>
            <br/>
            <form action="${pageContext.request.contextPath}/app/leaderboard/" method="POST">
                Nimi: <input type="text" name="leaderboardName" id="leaderboardName"/>
                      <input type="submit"/>
            </form>
            <br/>
            <h3>Lisää käyttäjiä leaderboardiin</h3>
            <br/>
            <c:forEach var="leaderboard" items="${leaderboards}">
            <a href=${pageContext.request.contextPath}/app/viewLeaderboard?leaderboardId=${leaderboard.id}>Leaderboard: ${leaderboard.name}</a>
            <form action="${pageContext.request.contextPath}/app/userToLeaderboard" method="POST">
                <select name="userIdParam" multiple="true">         
                    <c:forEach var="user" items="${users}">
                        <option value="<c:out value="${user.id}" />"><c:out value="${user.username}" /></option>
                    </c:forEach>
                </select>
                <c:if test="${userToLbError != null}">
                    Valitse vähintään yksi käyttäjä, jonka haluat lisätä leaderboardiin!<br/>
                </c:if>
               <input type="hidden" name="leaderboardId" id="leaderboardId" value="${leaderboard.id}"/>
               <input type="submit" name="submit" value="Lisää käyttäjiä" />
           </form>
        </c:forEach>
        </c:if>
        </div>
       </div>
    </body>
</html>
