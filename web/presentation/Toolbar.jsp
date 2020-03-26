<%@page import="banca.logic.Usuario"%>
<% Usuario admin = (Usuario)session.getAttribute("admin"); %>
<% Usuario client = (Usuario)session.getAttribute("cliente"); %>
<div class="Toolbar">
    <% if (client!=null && admin == null ){ %>
    <ul id="menu">
        <li><%=client.toString()%>
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
       <% if (admin!=null  && client == null){ %>
    <ul id="menu">
        <li><%=admin.toString()%>
            <ul id="Admin_ul">
                <li>Actualizar datos</li>
                <li>ver datos</li>
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
               <a href="/BancoUNA/presentation/admin/Retiro/show">
               <li>Retiro</li>
          </a>
            </ul>
        </li>
        <a href="/BancoUNA/presentation/admin/login/logout">
          <li>Logout</li></a>
        
    </ul>    
   <% } %>
   <% if (admin!=null  && client != null){ %>
                    Tienes Una Sección Abierta ya deseas cerrarla ?
                    <a href="/BancoUNA/presentation/login/logout"><button>SI</button></a>
   <% } %>
</div>

