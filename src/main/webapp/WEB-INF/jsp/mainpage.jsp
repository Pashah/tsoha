<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tervetuloa Liikunta Leaderboardiin</title>
    </head>

    <body>
        <p>Moi! Tää on liikunta leaderboardin hieno etusivu! wuup wuup</p>
        
        <h2>Lisää suoritus</h2>

        <div>
            Lisää suoritus:<br/>
            <form action="${pageContext.request.contextPath}/app/accomplishment/" method="POST">
                Laji: <input type="text" name="sport" id="sport"/><br/>
                Kesto: <input type="text" name="lengthInMinutes" id="lengthInMinutes"/><br/>
                <input type="submit"/>            
            </form>
        </div>
    </body>
</html>
