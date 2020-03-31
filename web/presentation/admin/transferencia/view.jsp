<%@page import="banca.logic.Moneda"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="banca.logic.Cuenta"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banca.presentation.Admin.transferencia.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Admin Transferencia</title>
    </head>
    <%@ include file="/presentation/admin/Header.jsp" %>
    <%@include file="/presentation/admin/Toolbar.jsp" %>
    <br>
    <% Model model = (Model) request.getAttribute("model"); %>
    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>
<%if(admin!=null){%>

 <div class="EspacioLogin"></div>
<div class="EspacioLogin" id="loginP">
     <div class="FormT">
     <div class="fila encabezado"><b><p>Menu Transferencia</b></p></div>
        <%if(model.getCliente_S().getCuentas().isEmpty() || model.getCliente_D().getCuentas().isEmpty()){%>
      <form action="/BancoUNA/presentation/admin/transferencia/show" method="post">
         
          <div class="fila encabezado"><b><p>Cliente De Salida</b></p></div>
        <div class="fila">
                        <div class="etiqueta">Cedula Cliente</div>
                        <input id="chekCed_S" type="checkbox"  onchange="document.getElementById('Cedula_S').disabled = !this.checked,
                                        document.getElementById('Numero_S').value = '',
                                        document.getElementById('Cedula_S').value = '',
                                        document.getElementById('Numero_S').disabled = this.checked,
                                        document.getElementById('chekNum_S').checked = !this.checked;"
                                        checked />
                       <input type="text" placeholder="102340567" name="Cedula_S" id="Cedula_S"
                               class="<%=erroneo("Cedula_S", errores)%>"
                               title="<%=title("Cedula_S", errores)%>"
                              value="<%=title("Valor_S1", errores)%>" 
                      required>
        </div>
         <br>
       <div class="fila">
                        <div class="etiqueta">Numero De Cuenta</div>
                        <input id="chekNum_S" type="checkbox" onchange="document.getElementById('Numero_S').disabled = !this.checked,
                                        document.getElementById('Numero_S').value = '', 
                                         document.getElementById('Cedula_S').value = '',
                                        document.getElementById('Cedula_S').disabled = this.checked,
                                        document.getElementById('chekCed_S').checked = !this.checked;" 
                                         />
                        <input type="text" placeholder="numero" name="Numero_S" id="Numero_S" disabled
                               class="<%=erroneo("Numero_S", errores)%>"
                               title="<%=title("Numero_S", errores)%>"
                               value="<%=title("Valor_S2", errores)%>" 
                               required>             
         </div>
       <br>
       <div class="fila encabezado"><b><p>Cliente De Destino</b></p></div>
        <div class="fila">
                        <div class="etiqueta">Cedula Cliente</div>
                        <input id="chekCed_D" type="checkbox"  onchange="document.getElementById('Cedula_D').disabled = !this.checked,
                                        document.getElementById('Numero_D').value = '',
                                        document.getElementById('Cedula_D').value = '',
                                        document.getElementById('Numero_D').disabled = this.checked,
                                        document.getElementById('chekNum_D').checked = !this.checked;"
                                        checked />
                       <input type="text" placeholder="102340567" name="Cedula_D" id="Cedula_D"
                               class="<%=erroneo("Cedula_D", errores)%>"
                               title="<%=title("Cedula_D", errores)%>"
                              value="<%=title("Valor_D1", errores)%>" 
                      required>

        </div>
         <br>
       <div class="fila">
                        <div class="etiqueta">Numero De Cuenta</div>
                        <input id="chekNum_D" type="checkbox" onchange="document.getElementById('Numero_D').disabled = !this.checked,
                                        document.getElementById('Numero_D').value = '', 
                                         document.getElementById('Cedula_D').value = '',
                                        document.getElementById('Cedula_D').disabled = this.checked,
                                        document.getElementById('chekCed_D').checked = !this.checked;" 
                                         />
                        <input type="text" placeholder="numero" name="Numero_D" id="Numero_D" disabled
                               class="<%=erroneo("Numero_D", errores)%>"
                               title="<%=title("Numero_D", errores)%>"
                               value="<%=title("Valor_D2", errores)%>" 
                               required>         
         </div>
       <br>
       <div class="fila encabezado"><button    id="Butonbuscar"  style="margin-bottom: 15px">Buscar</button> </div>
 </form>
<%}else if (!model.isListo()){%>   
<form action="/BancoUNA/presentation/admin/transferencia/add" method="post">
<div class="fila encabezado"><b><p><%=model.getCliente_S().getNombre()%></b></p></div>
     <div class="fila">
              <div class="etiqueta"> Selecione Cuenta al retirar :
                  <select name="Numero_C1" id="Numero_C1" required>
                           <%for(Cuenta f:model.getCliente_S().getCuentas()){%>
                                <option  value="<%=f.getNumero()%>"><%=f.toString()%> </option>
                            <%}%>          
                   </select>
               </div>               
</div>
<br>
<div class="fila">
    <div class="etiqueta">Monto a retirar</div>
    <div class="campo"><input  placeholder="Monto" type="text" name="Monto_T" 
                               class="<%=erroneo("Monto_T", errores)%>"
                               title="<%=title("Monto_T", errores)%>"
                               value="<%=form.get("Monto_T")[0]%>"
                               required>
    </div>
     <select name="Moneda_C" id="Moneda_C" required>
                           <%for(Moneda m:model.getMonedas()){%>
                                <option  value="<%=m.getTipo_cambio()%>"><%=m.getId()%> </option>
                            <%}%>          
     </select>
</div>
<br>
<div class="fila encabezado"><b><p><%=model.getCliente_D().getNombre()%></b></p></div>
     <div class="fila">
              <div class="etiqueta"> Selecione Cuenta a Transferir :
                  <select name="Numero_C2" id="Numero_C2" required>
                           <%for(Cuenta f:model.getCliente_D().getCuentas()){%>
                                <option  value="<%=f.getNumero()%>"><%=f.toStringFavorita()%> </option>
                            <%}%>          
                   </select>
               </div>               
</div>
<br>

<div class="fila">
    <div class="etiqueta">Motivo</div>
    <div class="campo"><input  placeholder="Motivo" type="text" name="Motivo_T" 
                               class="<%=erroneo("Motivo_T", errores)%>"
                               title="<%=title("Motivo_T", errores)%>"
                               value="<%=form.get("Motivo_T")[0]%>"
                               required>
    </div>
</div>
<br />
<div class="fila encabezado"><button    id="Butonbuscar"  style="margin-bottom: 15px">Transferir</button> </div>
 </form>
     
<%}else{%> 
<%
                          DecimalFormat df2 = new DecimalFormat("#.##");
                          df2.setRoundingMode(RoundingMode.DOWN);  
%>
 <div class="fila encabezado"><b><p>Retiro Realizado </b></p></div>   
 <div class="fila"> Fecha :  <%=model.getMovimiento_S().getFecha()%></div>
 <div class="fila"> Nombre :  <%=model.getMovimiento_S().getCuenta().getUsuario().toString()%></div>
 <div class="fila"> Monto :  <%=df2.format(model.getMovimiento_S().getRetiro().getMonto())%>
 <%=model.getMovimiento_S().getCuenta().getMoneda().getId()%></div>
     
  <div class="fila encabezado"><b><p>Deposito Realizado </b></p></div>   
 <div class="fila"> Fecha :  <%=model.getMovimiento_D().getFecha()%></div>
 <div class="fila"> Depositante :  <%=model.getMovimiento_D().getDeposito().getNombreDepositante()%></div>
 <div class="fila"> Motivo :  <%=model.getMovimiento_D().getDeposito().getMotivo()%></div>
 <div class="fila"> Monto :  <%=df2.format(model.getMovimiento_D().getDeposito().getMonto())%>
     <%=model.getMovimiento_D().getCuenta().getMoneda().getId()%> </div>
 
<%}%>   
<%}%>   
</html>
<%!
    private String erroneo(String campo, Map<String,String> errores){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return "is-invalid";
      else
        return "";
    }

    private String title(String campo, Map<String,String> errores){
      if ( (errores!=null) && (errores.get(campo)!=null) )
        return errores.get(campo);
      else
        return "";
    }

    private Map<String,String[]> getForm(Model model){
       Map<String,String[]> values = new HashMap<>();
       values.put("Monto_T", new String[]{String.valueOf(model.getRetiro().getMonto())});
       values.put("Motivo_T", new String[]{String.valueOf(model.getDeposito().getMotivo())});
       return values;
    }
    
%> 
