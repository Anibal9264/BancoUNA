<%@include file="/presentation/Model.jsp" %>
<%@page import="javax.servlet.ServletException"%>
<header>
    <div class="columna">
         <div class="logo">
           <a href="/BancoUNA/presentation/admin/Index.jsp">
               <img style=" margin: 10px" src="/BancoUNA/images/logo.png">
            </a>
         </div> 
    </div>
    
    <div class="columnadoble">
    </div>
     <%if(admin == null){ %>
        <div class="columna">
        <a href="/BancoUNA/presentation/admin/login/show">
        <img style=" margin: 30px" id="online" src="/BancoUNA/images/logoenlinea.png">
        </a>
       </div>
     <%}%>
    </header>            
