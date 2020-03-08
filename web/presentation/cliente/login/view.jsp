
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
             <p>Presione
                <a href="/BancoUNA/presentation/Index.jsp"> AQUI </a> para volver a la página principal
                </p>
            <div class="EspacioLogin"></div>
            <div class="EspacioLogin" id="loginP">
                <div class="login">
                     <img src="/BancoUNA/images/logoenlinea.png">
                </div>
                <div id="id" class="login">
                    <p>CEDULA : 
                    <input id="Cedula" ><br> 
                     CONTRASEÑA : 
                    <input type="password" id="password"><br>
                    </p>
                       <a href="/BancoUNA/presentation/login/login">
                           <button type="button">LOGIN</button>
                        </a>
                   
                </div> 
            </div>
            <div> Si no posee una cuenta y desea regístrarse ingrese al siguiente link:</div><br> 
            <div class="EspacioLogin"></div>
        </div>
    </body>
</html>
