<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" >
        <title>Suoritusten hallinta</title>
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
        <h1>Suoritusten hallinta</h1>
        <br/>
        
        <br/>
        <c:if test="${userId != null}">
            <h3>Lisää suoritus</h3>
            <br/>
        
        
            <br/>
            <form:form modelAttribute="accomplishment" action="${pageContext.request.contextPath}/app/accomplishment/" method="POST">
                Laji: <form:input path="sport" type="text" name="sport" id="sport"/><form:errors path="sport" /><br/>
                Kesto minuuteissa: <form:input path="lengthInMinutes" type="text" name="lengthInMinutes" id="lengthInMinutes"/><form:errors path="lengthInMinutes" /><br/>
                <form:input path="user_id" type="hidden" name="userId" id="userId" value="${userId}"/>
                <input type="submit"/>
                <c:if test="${addAccomplishmentError != null}">
                    Syötä minuutit kokonaislukuna! <br/>
                </c:if>
            </form:form>
       </c:if>
       <br/>
        <h3>Muokkaa tai poista suoritus</h3>
        <br/>
        <c:forEach var="acc" items="${accomplishments}">
          <ul>
            <li>
                 Laji: ${acc.sport} 
                 Kesto: ${acc.lengthInMinutes}
                 Pisteet: ${acc.points}
                 <c:if test="${userId == 1}">
                    <form action="${pageContext.request.contextPath}/app/setPoints?id=${acc.id}" method="POST">
                        <input type="text" name="points" id="points"/>
                        <input type="hidden" name="userId" id="userId" value="${acc.user_id}"/>
                        <button type="submit" style="height: 25px; width: 120px">Aseta pisteet</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/app/deleteAccomplishment?id=${acc.id}" method="POST">
                        <button type="submit" style="height: 25px; width: 80px">Poista suoritus</button>
                    </form>
                 </c:if>
             </li>
         </ul>
       </c:forEach>
      </div>
    </div>
    </body>
</html>
