
<div class="Toolbar">
    <%if(cliente != null){
    if (!cliente.getCedula().equals("null") && admin == null){ %>
    <ul id="menu">
        <li><%=cliente.toString()%>
            <ul id="User_ul">
                <li>Actualizar Mis datos</li>
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
   <% } %>
   
   <%if(admin != null){ 
     if (!admin.getCedula().equals("null") && cliente == null){ %>
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
   <% }
   }%>
  <%
      if(admin != null && cliente !=null){
      if (!admin.getCedula().equals("null")  &&!cliente.getCedula().equals("null") ){ %>
                    Tienes Una Sección Abierta ya deseas cerrarla ?
                    <a href="/BancoUNA/presentation/login/logout"><button>SI</button></a>
   <% }} %>
</div>

