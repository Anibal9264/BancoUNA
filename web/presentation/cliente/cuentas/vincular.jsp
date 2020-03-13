<%@page import="banca.presentation.cuentas.Model"%>
<%@page import="banca.logic.Cuenta"%>
<%
    Model model = (Model) request.getAttribute("model");
    Cuenta cuenta = model.getSeleccionado();
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
<%if(cuenta != null){%>
<body>
    
</body>
<%}%>
</html>