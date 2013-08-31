<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
        
            <h1>Tervetuloa leaderboardiin</h1>
            <br/>
        <c:if test="${userId == null}">
            <p>Kirjaudu sisään tai rekisteröidy niin pääset käyttämään eri ominaisuuksia</p>
        </c:if>
            <h2>
                <a href=${pageContext.request.contextPath}/app/viewUserPage>Käyttäjäsivut</a>
            </h2>    
            
        <c:if test="${userId != null}">
        <h2>Lisää suoritus</h2>
        
        <div>
            Lisää suoritus:<br/>
            <form:form modelAttribute="accomplishment" action="${pageContext.request.contextPath}/app/accomplishment/" method="POST">
                Laji: <form:input path="sport" type="text" name="sport" id="sport"/><form:errors path="sport" /><br/>
                Kesto: <form:input path="lengthInMinutes" type="text" name="lengthInMinutes" id="lengthInMinutes"/><form:errors path="lengthInMinutes" /><br/>
                <form:input path="user_id" type="hidden" name="userId" id="userId" value="${userId}"/>
                <input type="submit"/>            
            </form:form>
        </div>
        
        <div>
            Lisää leaderboard:<br/>
            <form action="${pageContext.request.contextPath}/app/leaderboard/" method="POST">
                Nimi: <input type="text" name="leaderboardName" id="leaderboardName"/>
                <input type="submit"/>
            </form>
        </div>
       </c:if>
       <br/>
       <br/>
       <h3>Leaderboardit:</h3>
       <c:forEach var="leaderboard" items="${leaderboards}">
          <ul>
                <li>
                    <a href=${pageContext.request.contextPath}/app/viewLeaderboard?leaderboardId=${leaderboard.id}>${leaderboard.name}</a>
                </li>
          </ul>
                <p>Lisää käyttäjiä leaderboardiin:</p>
        <form action="${pageContext.request.contextPath}/app/userToLeaderboard" method="POST">
            <select name="userIdParam" multiple="true">         
                <c:forEach var="user" items="${users}">
                    <option value="<c:out value="${user.id}" />"><c:out value="${user.username}" /></option>
                </c:forEach>
            </select>
           <input type="hidden" name="leaderboardId" id="leaderboardId" value="${leaderboard.id}"/>
           <input type="submit" name="submit" value="Lisää käyttäjiä" />
       </form>
      </c:forEach>     
    </body>
</html>
