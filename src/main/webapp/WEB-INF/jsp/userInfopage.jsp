<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Käyttäjän ${user.username} tiedot</title>
    </head>
    <body>
        <h1>${user.username} tiedot:</h1>
        
        <ul>
            <li>
                Käyttäjänimi: ${user.username}
            </li>
            <li>
                Sähköposti: ${user.email}
            </li>
            <li>
                Pisteet: ${user.points}
            </li>
            <li>
                Rooli: ${user.role}
            </li>
        </ul>
        
        <h3>Leaderboardit: </h3>
        
        <c:forEach var="leaderboard" items="${leaderboards}">
          <ul>
                <li>
                    <a href=${pageContext.request.contextPath}/app/viewLeaderboard?leaderboardId=${leaderboard.id}>${leaderboard.name}</a>
                </li>
          </ul>
        </c:forEach>
        
        
        <h3>Suoritukset: </h3>
        
        <c:forEach var="accomplishment" items="${accomplishments}">
          <ul>
            <li>
                 Laji: ${accomplishment.sport} 
                 Kesto: ${accomplishment.lengthInMinutes}
                 Pisteet: ${accomplishment.points}
                 <c:if test="${userId == 1}">
                    <form action="${pageContext.request.contextPath}/app/setPoints?id=${accomplishment.id}" method="POST">
                        <input type="text" name="points" id="points"/>
                        <button type="submit" style="height: 25px; width: 120px">Aseta pisteet</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/app/deleteAccomplishment?id=${accomplishment.id}" method="POST">
                        <button type="submit" style="height: 25px; width: 80px">Poista suoritus</button>
                    </form>
                 </c:if>
             </li>
         </ul>
       </c:forEach>
        
    </body>
</html>
