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
 <body>
     <div class="Tabla-cuentas">
        <h1>Listado de Cuentas del Cliente</h1>     
    
        <table>
            <thead class="Tabla-in" id="cabeza">
                <tr> <td>Cuenta Numero</td><td>Nombre</td><td>Saldo</td><td>Moneda</td><td>Estado</td>   </tr>
            </thead>
            <tbody class="Tabla-in">
                        <% for(Cuenta c:cuentas){%>
                <tr> <td><%=c.getNumero()%> </td><td><%=c.getDescripcion()%></td><td><%=c.getSaldo()%></td> <td><%=c.getMoneda().getId()%></td><td><%=c.getEstado()%></td>  </tr>
                        <%}%>
            </tbody>
        </table>          
    </div>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
</html>