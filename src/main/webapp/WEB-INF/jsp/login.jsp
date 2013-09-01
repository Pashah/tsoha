<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login sivu</title>
    </head>
    <body>
        <h1>Kirjaudu sisään!</h1>
        <form action="${pageContext.request.contextPath}/app/login" method="POST">
            <label>Käyttäjätunnus:</label> <input type="text" name="username" id="username" /><br/>
            <label>Salasana:</label> <input type="password" name="password" id="password" /><br/>
            <c:if test="${error != null}">
                Kirjautuminen epäonnistui, tarkista käyttäjätunnus ja salasana!<br/>
            </c:if>  
            <input type="submit" id="login" value="Kirjaudu" />
        </form>
            <br/>
            <br/>
            <a href=${pageContext.request.contextPath}/app/mainpage>Takaisin pääsivulle</a>
    </body>
</html>