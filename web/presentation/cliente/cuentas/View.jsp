
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
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
    DecimalFormat df2 = new DecimalFormat("#.##");
    df2.setRoundingMode(RoundingMode.DOWN);  
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
<%if(cliente!=null){%>
<%if(!model.getCuentas().isEmpty()){%>
<body>
        <h1>Listado de Cuentas del Cliente</h1>     
        <div class="Tabla-Campo" id="cuenta">
 <table class="Tabla-in" id="cabeza">
             <tr> <td> <b>Cuenta Número</b></td>
                 <td><b>Nombre</b></td>
                 <td><b>Saldo</b></td>
                 <td><b>Moneda</b></td>
                 <td><b>Estado</b></td>
                 <td><b>Detalle</b></td>   </tr>
 </table>
 <table  class="Tabla-in" id="cuerpo">
                        <%for(Cuenta c:cuentas){%>
                       <tr> <td><%=c.getNumero()%> </td><td><%=c.getDescripcion()%></td><td><%=df2.format(c.getSaldo())%></td>
                             <td><%=c.getMoneda().getId()%></td><td><%=c.getEstado()%></td>
                             <td><a href="/BancoUNA/presentation/cliente/cuentas/detalles?numeroFld=<%=c.getNumero()%>">
                             ver</a></td></tr>
                        <%}%>                     
 </table>
                 
    </div>
     <%@ include file="/presentation/Footer.jsp" %>
<%}else{%>
       <div class="fila encabezado"><p><b>Detalles de Cuenta </b></p></div>
       <%if(!model.getMovimientos().isEmpty()){%>
<div class="fila encabezado">
    <form action="/BancoUNA/presentation/cliente/cuentas/acomodo?numeroFld=<%=model.getMovimientos().get(0).getCuenta().getNumero()%>" method="post">
        Desde: <input type="date" name="fecha1" required step="1" min="2018-01-01"  value=<%=GetFecha()%>>
Hasta: <input type="date" name="fecha2" step="1" required min="2018-01-01"  value=<%=GetFecha()%>>
<button    id="Butonbuscar"  style="margin-bottom: 15px">Buscar</button>
</div>

<div class="Tabla-Campo">
    <table class="Tabla-in" id="cabeza">
                <tr> <td>Fecha</td><td>Tipo</td><td>Detalle</td><td>Monto</td><td>Moneda</td></tr>
    </table>
    <table  class="Tabla-in" id="cuerpo">
                        <% for(Movimiento m:movimientos){%>
                        <tr> <td><%=m.getFecha()%> </td><td><%=m.toStringTipo()%></td><td><%=m.getDetalle()%></td>
                        <td><%if(m.getDeposito().getId()!=0){%>
                            <%=df2.format(m.getDeposito().getMonto())%>
                        <%}else if(m.getRetiro().getId()!=0){%>
                            <%=df2.format(m.getRetiro().getMonto())%>
                        <%}%>
                        </td>
                        <td><%=m.getCuenta().getMoneda().getId()%></td>
                        </tr>
                        <%}%>
     </table>
       
</div>
       <%}else{%> 
       <br>
       <div style="text-align: center">NO TIENE MOVIMIENTOS</div>
        <%}%>
     <%@ include file="/presentation/Footer.jsp" %>
</body>
<%}%>
<%}%>
</html>
<%!
String GetFecha(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
   }
%>