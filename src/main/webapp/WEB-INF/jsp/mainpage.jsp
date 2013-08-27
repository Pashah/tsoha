<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tervetuloa Liikunta Leaderboardiin</title>
    </head>
    <c:if test="${userId == null}">
        <a href=${pageContext.request.contextPath}/app/register>Rekisteröidy</a>
        <a href=${pageContext.request.contextPath}/app/login>Kirjaudu sisään</a>
    </c:if>
    <c:if test="${userId != null}">
        <a href=${pageContext.request.contextPath}/app/logout>Kirjaudu ulos</a>
    </c:if>
    <body>
        <p>Moi! Tää on liikunta leaderboardin hieno etusivu! wuup wuup</p>
        
        <h2>Lisää suoritus</h2>

        <div>
            Lisää suoritus:<br/>
            <form action="${pageContext.request.contextPath}/app/accomplishment/" method="POST">
                Laji: <input type="text" name="sport" id="sport"/><br/>
                Kesto: <input type="text" name="lengthInMinutes" id="lengthInMinutes"/><br/>
                <input type="hidden" name="userId" id="userId" value="${userId}"/>
                <input type="submit"/>            
            </form>
        </div>
        <div>
            Lisää leaderboard:<br/>
            <form action="${pageContext.request.contextPath}/app/leaderboard/" method="POST">
                Nimi: <input type="text" name="leaderboardName" id="leaderboardName"/>
                <input type="submit"/>
            </form>
        </div>
        <c:forEach var="accomplishment" items="${accomplishments}">
          <ul>
            <li>
                 Laji: ${accomplishment.sport} 
                 Kesto: ${accomplishment.lengthInMinutes}
                 Pisteet: ${accomplishment.points}
                 <form action="${pageContext.request.contextPath}/app/setPoints?id=${accomplishment.id}" method="POST">
                    <input type="text" name="points" id="points"/>
                    <button type="submit" style="height: 25px; width: 80px">set points</button>
                 </form>
                 <form action="${pageContext.request.contextPath}/app/deleteAccomplishment?id=${accomplishment.id}" method="POST">
                      <button type="submit" style="height: 25px; width: 80px">delete</button>
                 </form>  
             </li>
          </ul>
       </c:forEach>
       <c:forEach var="leaderboard" items="${leaderboards}">
          <ul>
                <li>
                    Leaderboardin nimi: ${leaderboard.name}
                </li>
          </ul>
        <form action="${pageContext.request.contextPath}/app/userToLeaderboard" method="POST">
            <select name="userIdParam" multiple="true">         
                <c:forEach var="user" items="${users}">
                    <option value="<c:out value="${user.id}" />"><c:out value="${user.username}" /></option>
                </c:forEach>
            </select>
           <input type="hidden" name="leaderboardId" id="leaderboardId" value="${leaderboard.id}"/>
           <input type="submit" name="submit" />
       </form>
      </c:forEach>
                
    </body>
</html>
