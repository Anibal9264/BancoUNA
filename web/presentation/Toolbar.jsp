<div class="Toolbar">
    <% if (user!=null){ %>
    <ul id="menu">
        <li><%=user.toString()%>
            <ul id="User_ul">
                <li>Actualizar datos</li>
                <li>ver datos</li>
            </ul>
        </li>
        <li>Cuentas
            <ul id="Cuenta_ul">
                <a href="/BancoUNA/presentation/cliente/cuentas/show">
                    <li>Posici�n Cliente</li>
                </a>
                <a href="/BancoUNA/presentation/cliente/vincular/show">
                    <li>Vincular Cuenta</li>
                </a>
            </ul>
        </li>
        <li>Transferencia
            <ul id="Transferencia_ul">
               <li>cuenta propia</li>
          <a href="/BancoUNA/presentation/transferencia/show">
               <li>cuentas favoritas</li>
          </a>
            </ul>
        </li>
        <a href="/BancoUNA/presentation/login/logout">
          <li>Logout</li></a>
        
    </ul>    
   <% } %>
</div>

