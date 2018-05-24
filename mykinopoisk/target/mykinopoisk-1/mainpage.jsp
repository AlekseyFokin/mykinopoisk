<%-- 
    Document   : mainpage
    Created on : 12.05.2018, 17:25:56
    Author     : sniffsnirr
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SearchingResultsPage</title>
        <link rel="stylesheet" type="text/css" href="./style.css" />
    </head>
     <body>
        <div id="container">

            <div id="header"> <h2>Результат поиска фильма по названию    ${movieTitle}</h2></div>
            <div id="menu"> 
                           <form name="commandForm" method="POST" action="mkservlet">
                                        <br><br><br>
                                        <button type="submit" name="command" value="savetocsv">Сохранить в *.csv</button>
                                        <!--<input type="submit"  name="command" value="savetocsv"/>-->
                                       <br><br><br>
                                       <button type="submit" name="command"  value="savetodb">Сохранить в БД</button>
                                       <!--<input type="submit"  name="command"  value="savetodb"/>-->
                                       <br><br><br>
                                       <button type="submit" name="command"  value="gotosearch">На страницу поиска</button>
                                       <!--<input type="submit" name="command"  value="gotosearch" />-->
                            </form>
                
                   </div>
                        <div id="content">
                            <table border="1">
                            <jsp:useBean id="listMovies" class="java.util.HashSet" scope="session"/>  
                                            <c:forEach items="${listMovies}" var="movie">  
                                                <tr>
                                                        <td align="left">${movie.name}</td>  
                                                        <td align="left"><img src="${movie.pic}" alt="${movie.name}" title="${movie.name}"></td>  
                                                        <td align="left"><c:forEach items="${movie.actors}" var="actor">  
                                                                                    <img src="${actor.pic}" alt="${actor.fio}" title="${actor.fio}">
                                                                                    </c:forEach>
                                                        </td>  
                                                        <td align="left">${movie.startyear}</td>  
                                                        <td align="left">${movie.auntr}</td>  
                                                        <td align="left">${movie.lengthtime}</td>  
                                                 </tr>
                                            </c:forEach>
                                            
                            </table>             
                            
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
