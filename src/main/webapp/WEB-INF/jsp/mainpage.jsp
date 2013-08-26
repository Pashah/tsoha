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
        <c:forEach var="accomplishments" items="${accomplishments}">
          <ul>
            <li>
                 Laji: ${accomplishments.sport} 
                 Kesto: ${accomplishments.lengthInMinutes}
                 Pisteet: ${accomplishments.points}
                 <form action="${pageContext.request.contextPath}/app/setPoints?id=${accomplishments.id}" method="POST">
                    <input type="text" name="points" id="points"/>
                    <button type="submit" style="height: 25px; width: 80px">set points</button>
                 </form>
                 <form action="${pageContext.request.contextPath}/app/deleteAccomplishment?id=${accomplishments.id}" method="POST">
                      <button type="submit" style="height: 25px; width: 80px">delete</button>
                 </form>  
             </li>
          </ul>
       </c:forEach>
    </body>
</html>
