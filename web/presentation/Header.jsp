<%@page import="banca.logic.Usuario"%>
<% Usuario user = (Usuario)session.getAttribute("usuario"); %>
<header>
    <div class="columna">
         <div class="logo">
           <a href="/BancoUNA/presentation/Index.jsp">
               <img src="/BancoUNA/images/logo.png">
            </a>
         </div> 
    </div>
    <div class="columnadoble">
        <div class="tipoCambio">Tipo de cambio de dolar</div> 
    </div>
    <div class="columna">
        <%if(user==null){ %>
        <a href="/BancoUNA/presentation/cliente/login/view.jsp">
        <img id="online" src="/BancoUNA/images/logoenlinea.png">
        </a>
        <%}%>
    </div>
</header>            

