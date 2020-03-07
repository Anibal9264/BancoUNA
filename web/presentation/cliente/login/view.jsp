
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@ include file="/presentation/Head.jsp" %>
     <title>Login</title>
    </head>
    <header>
     <div class="columna">
         <div class="logo">
           <a href="/BancoUNA/presentation/Index.jsp">
               <img src="/BancoUNA/images/logo.png">
            </a>
         </div> 
    </div>
    <div class="hr"></div>
    </header>
    <body>
        <div class="fila">
            <div class="EspacioLogin"></div>
            <div class="EspacioLogin" id="loginP">
                <div class="login">
                     <img src="/BancoUNA/images/logoenlinea.png">
                </div>
                <div id="id" class="login">
                    CEDULA <br>
                    <input id="Cedula" >   
                       <a href="/BancoUNA/presentation/login/login">
                           <button type="button">login!</button>
                        </a>
                        
                   
                </div>
                
            </div>
            <div class="EspacioLogin"></div>
        </div>
    </body>
</html>
