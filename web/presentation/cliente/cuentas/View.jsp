<%@page import="java.text.DecimalFormat"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="banca.presentation.Cliente.cuentas.Model"%>
<%@page import="java.util.List"%>
<%@page import="banca.logic.Cuenta"%>
<%@page import="banca.logic.Movimiento"%>
<%
    Model model = (Model) request.getAttribute("model");
    List<Cuenta> cuentas = model.getCuentas();
    List<Movimiento> movimientos = model.getMovimientos();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@ include file="/presentation/Head.jsp" %>
     <title>Cuentas</title>
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
                        <%
                          DecimalFormat df2 = new DecimalFormat("#.##");
                          df2.setRoundingMode(RoundingMode.DOWN);  
                    for(Cuenta c:cuentas){%>
                       <tr> <td><%=c.getNumero()%> </td><td><%=c.getDescripcion()%></td><td><%=df2.format(c.getSaldo())%></td>
                             <td><%=c.getMoneda().getId()%></td><td><%=c.getEstado()%></td>
                             <td><a href="/BancoUNA/presentation/cliente/cuentas/detalles?numeroFld=<%=c.getNumero()%>">
                             ver</a></td></tr>
                        <%}%>                     
            </tbody>
        </table>          
    </div>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
<%}else{%>
<body>
     <div class="Tabla-cuentas">
         <h1>Detalles de Cuenta</h1>     
       <%if(!model.getMovimientos().isEmpty()){%>
        <table>
            <thead class="Tabla-in" id="cabeza">
                <tr> <td>Fecha</td><td>Tipo</td><td>Detalle</td><td>Monto</td></tr>
            </thead>
            <tbody class="Tabla-in">
                        <% for(Movimiento m:movimientos){%>
                        <tr> <td><%=m.getFecha()%> </td><td><%=m.toStringTipo()%></td><td><%=m.getDetalle()%></td>
                        <td><%if(m.getDeposito().getId()!=0){%>
                            <%=m.getDeposito().getMonto()%>
                        <%}else if(m.getRetiro().getId()!=0){%>
                            <%=m.getRetiro().getMonto()%>
                        <%}%>
                        </td></tr>
                        <%}%>
            </tbody>
        </table>
       <%}else{%> 
       <div>NO TIENE MOVIMIENTOS!!</div>
        <%}%>
    </div>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
<%}%>
</html>
