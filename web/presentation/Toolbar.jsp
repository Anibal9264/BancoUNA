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
                    <li>Posición Cliente</li>
                </a>
                <a href="/BancoUNA/presentation/cliente/cuentas/vincular">
                    <li>Vincular Cuenta</li>
                </a>
            </ul>
        </li>
        <li>Transferencia
            <ul id="Transferencia_ul">
               <li>cuenta propia</li>
               <li>cuentas favoritas</li>
            </ul>
        </li>
        <a href="/BancoUNA/presentation/login/logout">
          <li>Logout</li></a>
        
    </ul>    
   <% } %>
</div>

