<%@page import="banca.logic.Usuario"%>
<% Usuario cliente = (Usuario)session.getAttribute("cliente"); %>
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
        <%if(cliente==null){ %>
        <a href="/BancoUNA/presentation/login/show">
        <img id="online" src="/BancoUNA/images/logoenlinea.png">
        </a>
        <%}%>
    </div>
</header>            

