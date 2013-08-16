<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <input type="submit" id="login" value="Kirjaudu" />
        </form>
    </body>
</html>
