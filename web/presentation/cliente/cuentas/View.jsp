

<%@page import="banca.logic.Cuenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
    List<Cuenta> cuentas= new ArrayList();
   
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="/presentation/Head.jsp" %>
 <title>Principal</title> 
</head>
<body >
    <%@ include file="/presentation/Header.jsp" %>

    <div><H1 >Cuentas</H1></div>
    <div><h1>Listado de Cuentas del Cliente</h1>  </div>    
    <table style=" background-color: black;">
        <thead>
            <tr> <th>Numero</th><th>Saldo</th></tr>
        </thead>
        
            <%for(Cuenta c:cuentas){%>
             <tr>
                <td><%=c.getNumero()%></td>
                <td><%=c.getSaldo()%></td>
                <td> <img src="/BancoUNA/images/money.png"> </td>
             </tr>
             <%}%>
 
    
    </table>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
</html>