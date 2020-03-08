<div class="Toolbar">
    <hr>

             <% if (user!=null){ %>
             <li class="toolbar">
                  <a href="/Guia/presentation/cliente/cuentas/show">Cuentas</a>
                  <ul>  <!--submenu --> </ul>
                </li>                        
                <li class="toolbar">
                  <a  href="#">User:<%=user.getCedula()%></a>
                  <ul>  <!--submenu --> </ul>
                </li> 
                <li class="toolbar">
                  <a  href="/BancoUNA/presentation/login/logout">Logout</a>
                  <ul>  <!--submenu --> </ul>
                </li>                
                        <% } %>
                        <% if (user==null){%>
                               
                        <% }%>             
 
</div>
