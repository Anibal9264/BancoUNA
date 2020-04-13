
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
    <%@ include file="/presentation/admin/Header.jsp" %>
    <%@include file="/presentation/admin/Toolbar.jsp" %>
    <br>
    <% Model model = (Model) request.getAttribute("model"); %>
<div  class="EspacioLogin"></div>
<div style="background-color: #943126" class="EspacioLogin" id="loginP">
<%if(model.getMovimientos().isEmpty()){%> 
<div style=" color: lightgray">
 <div  class="fila encabezado"><p><b>Acreditar intereses</b></p></div>
 <div class="etiqueta">Total de cuentas: <%=model.getCuentas().size()%></div> <br>
  <div class="fila encabezado">
      <a href="/BancoUNA/presentation/admin/acreditar/action" >
          <button    id="ingresar"  style="margin-bottom: 15px">Acreditar</button> </div>
      </a>
  </div>
    <%}else{%>
              <div class="fila encabezado"><p><b>Acreditaciones Realizadas</b></p></div>   
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