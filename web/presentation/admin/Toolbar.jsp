
<div class="Toolbar">
   <%if(admin != null){%>
    <ul id="menu">
        <li><%=admin.toString()%>
            <ul id="Admin_ul">
                <li>Actualizar datos de Un Cliente</li>
            </ul>
        </li>
        <li>Menu Agregar
            <ul id="addU_ul">
                <a href="/BancoUNA/presentation/admin/addUser/show">
                    <li>Agergar Usuario</li>
                </a>
                <a href="/BancoUNA/presentation/admin/addCuenta/show">
                    <li>Agergar Cuenta</li>
                </a>
            </ul>
        </li>
        <li>Transferencia
            <ul id="AdminT_ul">
               <a href="/BancoUNA/presentation/admin/deposito/show">
                    <li>Deposito</li>
                </a>
               <a href="/BancoUNA/presentation/admin/retiro/show">
               <li>Retiro</li>
               </a>
               <a href="/BancoUNA/presentation/admin/transferencia/show">
               <li>Transferencia</li>
               </a>
                <a href="/BancoUNA/presentation/admin/acreditar/show">
               <li>Acreditar Intereses</li>
               </a>
            </ul>
        </li>
        <a href="/BancoUNA/presentation/admin/login/logout">
          <li>Logout</li></a>
        
    </ul>    
   <% }%>
</div>

