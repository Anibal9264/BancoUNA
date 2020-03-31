
<%@page import="banca.logic.Movimiento"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="banca.presentation.Admin.acreditar.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Acreditar</title>
    </head>
    <%@ include file="/presentation/Header.jsp" %>
    <%@include file="/presentation/Toolbar.jsp" %>
    <br>
    <% Model model = (Model) request.getAttribute("model"); %>
<div class="EspacioLogin"></div>
<div class="EspacioLogin" id="loginP">
<%if(model.getMovimientos().isEmpty()){%> 
 <div class="fila encabezado"><b><p>Acredirar intereses</b></p></div>
 <div class="etiqueta">Total de cuentas: <%=model.getCuentas().size()%></div>
  <div class="fila encabezado">
      <a href="/BancoUNA/presentation/admin/acreditar/action" >
          <button    id="Butonbuscar"  style="margin-bottom: 15px">Acreditar</button> </div>
      </a>
    <%}else{%>
              <div class="fila encabezado"><b><p>Acreditaciones Realizadas</b></p></div>   
        <table>
            <thead class="Tabla-in" id="cabeza">
                <tr> <td> <b>Cuenta Número</b></td>
                    <td><b>Nombre</b></td>
                    <td><b>Monto acreditado</b></td>
                    <td><b>Moneda</b></td>
                </tr>
            </thead>
            <tbody class="Tabla-in">
                        <%
                          DecimalFormat df2 = new DecimalFormat("#.##");
                          df2.setRoundingMode(RoundingMode.DOWN);  
                    for(Movimiento m:model.getMovimientos()){%>
                       <tr> <td><%=m.getCuenta().getNumero()%> </td>
                              <td><%=m.getCuenta().getUsuario().toString()%></td>
                              <td><%=df2.format(m.getDeposito().getMonto())%></td>
                             <td><%=m.getCuenta().getMoneda().getId()%></td></tr>
                        <%}%>                     
            </tbody>
        </table> 
      
      <%}%>
 </div>
 </html>