<%@page import="banca.presentation.cuentas.Model"%>
<%@page import="java.util.List"%>
<%@page import="banca.logic.Cuenta"%>
<%
    Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentas = model.getCuentas();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@ include file="/presentation/Head.jsp" %>
     <title>Login</title>
    </head>
     <%@ include file="/presentation/Header.jsp" %>
     <%@ include file="/presentation/Toolbar.jsp" %>
<%if(!model.getCuentas().isEmpty()){%>
<body>
     <div class="Tabla-cuentas">
        <h1>Listado de Cuentas del Cliente</h1>     
    
        <table>
            <thead class="Tabla-in" id="cabeza">
                <tr> <td> <b>Cuenta Número</b></td><td><b>Nombre</b></td><td><b>Saldo</b></td><td><b>Moneda</b></td><td><b>Estado</b></td><td><b>Detalle</b></td>   </tr>
            </thead>
            <tbody class="Tabla-in">
                        <% for(Cuenta c:cuentas){%>
                        <tr> <td><%=c.getNumero()%> </td><td><%=c.getDescripcion()%></td><td><%=c.getSaldo()%></td>
                             <td><%=c.getMoneda().getId()%></td><td><%=c.getEstado()%></td>
                             <td><a href="/BancoUNA/presentation/cliente/cuentas/detalles"  onclick="<% 
                               session.setAttribute("CuentaFila",c);
                             %>">ver</a></td></tr>
                        <%}%>
            </tbody>
        </table>          
    </div>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
<%}%>


</html>
