<div class="Toolbar">
    <%if(cliente != null){%>
    <ul id="menu">
        <li><%=cliente.toString()%>
            <ul id="User_ul">
                <a href="/BancoUNA/presentation/cliente/actualizar/show">
                    <li>Actualizar mis datos </li>
                </a>
            </ul>
        </li>
        <li>Cuentas
            <ul id="Cuenta_ul">
                <a href="/BancoUNA/presentation/cliente/cuentas/show">
                    <li>Posición Cliente</li>
                </a>
                <a href="/BancoUNA/presentation/cliente/vincular/show">
                    <li>Vincular Cuenta</li>
                </a>
            </ul>
        </li>
        <li>Transferencia
             <ul id="Transferencia_ul">
            <a href="/BancoUNA/presentation/transferencia/propia/show">
               <li>Cuenta propia</li>
            </a>
            <a href="/BancoUNA/presentation/transferencia/show">
               <li>Cuentas favoritas</li>
          </a>
            </ul>
        </li>
        <a href="/BancoUNA/presentation/login/logout">
          <li>Logout</li></a>
        
    </ul>
   <% } %>
</div>

