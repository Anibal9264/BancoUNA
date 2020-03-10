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
     <div class="Tabla-cuestas">
        <h1>Listado de Cuentas del Cliente</h1>     
    
        <table>
            <thead>
                <tr> <td>Numero</td> <td>Saldo</td>  </tr>
            </thead>
            <tbody>
                        <% for(Cuenta c:cuentas){%>
                <tr> <td><%=c.getNumero()%> </td>  <td><%=c.getSaldo()%></td></tr>
                        <%}%>
            </tbody>
        </table>          
    </div>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
</html>