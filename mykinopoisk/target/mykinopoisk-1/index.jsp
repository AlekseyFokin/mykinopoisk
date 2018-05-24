<%-- 
    Document   : index
    Created on : 12.05.2018, 15:57:32
    Author     : sniffsnirr
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%request.setCharacterEncoding("UTF-8");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
               <link rel="stylesheet" type="text/css" href="./style.css" />
        <title>Search on kinopoisk.ru</title>
 
    </head>
    <body>
        <div id="container">

            <div id="header"> <h2>Поиск фильма по названию </h2> </div>

                      
                        <div id="menu"><h3>Введите название фильма </h3>  </div>
                        <div id="content">
                            <form name="searchForm" method="POST" action="mkservlet">
                                <input type="hidden" name="command" value="searchmovies"/>
                                <input  type="text" name="movieTitle"  value="Bladerunner"  />
                                <input type="submit" value="Найти"/>
                            </form>
                            <br/>
                            ${errorSearch}
                            <br/>
                            ${wrongAction}
                            <br/>
                            ${nullPage}
                                                        
                        </div>
                        <div id="footer"><h4>Автор сервиса Алексей Фокин</h4>
                                                     <a href="mailto:alfo12@mail.ru?subject=Добрый день. У меня вопрос по работе сервиса 'Поиск фильма по названию' ">Задавайте  вопросы по электронной почте</a>
                        </div>

            </div>
    </body>
</html>
