<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leaderboard</title>
    </head>
    <body>
        <h1>${leaderboard.name}</h1>
        
        <h2>Ranking</h2>
        <ol>
        <c:forEach var="user" items="${users}">
            
                <li>Käyttäjänimi: ${user.username}
                    Pisteet: ${user.points}</li>
            
            <br/>
        </c:forEach>
        </ol>
    </body>
</html>
