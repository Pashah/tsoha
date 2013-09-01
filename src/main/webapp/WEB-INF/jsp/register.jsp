<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rekisteröidy</title>
    </head>
    <body>
        <h1>Rekisteröidy</h1>
        <br/>
        
        <table>
           <form:form modelAttribute="user" action="${pageContext.request.contextPath}/app/register" method="POST">
                
                <tr><td>Käyttäjätunnus:</td> <td><form:input path="username" /><form:errors path="username" /></td></tr>
                <tr><td>Sähköposti:</td> <td><form:input path="email" /><form:errors path="email" /></td></tr>
                <tr><td>Salasana:</td> <td><form:input type="password" path="password" /><form:errors path="password" /></td></tr>           
                
                <tr><td/><td>
                        <input type="submit">
                </td></tr>
                <tr><td/><td>
                        <a href="<c:url value='login'/>">
                            <input type="button" value="Cancel"/>
                </td></tr>
                
            </form:form>
        </table>
        <br/>
        <br/>
        <a href=${pageContext.request.contextPath}/app/mainpage>Takaisin pääsivulle</a>
    </body>
</html>