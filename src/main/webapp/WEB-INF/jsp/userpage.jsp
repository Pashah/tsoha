<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Käyttäjäsivut</title>
    </head>
    <body>
        <h1>Käyttäjien tiedot</h1>
        
        <p>Täältä löydät kaikki käyttäjät, paina käyttäjänimeä niin pääset käyttäjän tietoihin käsiksi</p>
        
        <h3>Käyttäjät: </h3>
        
        <c:forEach var="user" items="${users}">
            <a href=${pageContext.request.contextPath}/app/viewUserInfo?userId=${user.id}>${user.username}</a>
        </c:forEach>
        
    </body>
</html>
