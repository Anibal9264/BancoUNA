<%@page import="java.math.RoundingMode"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="banca.logic.Moneda"%>
<%@page import="banca.logic.Cuenta"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banca.presentation.Admin.Retiro.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Admin Retiro</title>
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
     <div class="fila encabezado"><b><p>Menu Deposito</b></p></div>
        <%if(model.getCliente().getCuentas().isEmpty() && !model.getCuenta().isEstado()){%>
      <form action="/BancoUNA/presentation/admin/retiro/show" method="post">
        <div class="fila">
                        <div class="etiqueta">Cedula Cliente</div>
                        <input id="chekCed" type="checkbox"  onchange="document.getElementById('Cedula_C').disabled = !this.checked,
                                        document.getElementById('Numero_C').value = '',
                                        document.getElementById('Cedula_C').value = '',
                                        document.getElementById('Numero_C').disabled = this.checked,
                                        document.getElementById('chekNum').checked = !this.checked;"
                                        checked />
                       <input type="text" placeholder="102340567" name="Cedula_C" id="Cedula_C"
                               onclick="document.getElementById('ouput1').value ='' "
                               class="<%=erroneo("Cedula_C", errores)%>"
                               title="<%=title("Cedula_C", errores)%>"
                              value="<%=title("Cedula_C2", errores)%>" 
                      required>
                  <%if(!title("Cedula_C2", errores).isEmpty()){%>
                  <output id="ouput1">Cliente no existe</output>
                  <%}%>
        </div>
         <br>
       <div class="fila">
                        <div class="etiqueta">Numero De Cuenta</div>
                        <input id="chekNum" type="checkbox" onchange="document.getElementById('Numero_C').disabled = !this.checked,
                                        document.getElementById('Numero_C').value = '', 
                                         document.getElementById('Cedula_C').value = '',
                                        document.getElementById('Cedula_C').disabled = this.checked,
                                        document.getElementById('chekCed').checked = !this.checked;" 
                                         />
                        <input type="text" placeholder="numero" name="Numero_C" id="Numero_C" disabled
                               onclick="document.getElementById('ouput2').value ='' "
                               class="<%=erroneo("Numero_C", errores)%>"
                               title="<%=title("Numero_C", errores)%>"
                               value="<%=title("Numero_C2", errores)%>" 
                               required>
                   <%if(!title("Numero_C2", errores).isEmpty()){%>
                  <output id="ouput2">Cuenta no existe</output>
                  <%}%>               
         </div>
       <br>
       <div class="fila encabezado"><button    id="Butonbuscar"  style="margin-bottom: 15px">Buscar</button> </div>
 </form>
<%}else if(!model.isListo()){%>      
   <form action="/BancoUNA/presentation/admin/retiro/add" method="post">
     
     <%if(!model.getCliente().getCuentas().isEmpty()){%>   
     <div class="fila encabezado"><b><p><%=model.getCliente().getNombre()%></b></p></div>
     <div class="fila">
              <div class="etiqueta"> Selecione Cuenta al retirar :
                  <select name="Numero_C" id="Numero_C" required>
                           <%for(Cuenta f:model.getCliente().getCuentas()){%>
                                <option  value="<%=f.getNumero()%>"><%=f.toString()%> </option>
                            <%}%>          
                   </select>
               </div>               
          </div>
          <br>
      <%}%>
                    <div class="fila">
                        <div class="etiqueta">Monto a retirar</div>
                        <div class="campo"><input  placeholder="Monto" type="text" name="Monto_R" 
                                                    class="<%=erroneo("Monto_R", errores)%>"
                                                    title="<%=title("Monto_R", errores)%>"
                                                    value="<%=form.get("Monto_R")[0]%>"
                                                    required>
                        </div>
                   <select name="Moneda_C" id="Moneda_C" required>
                           <%for(Moneda m:model.getMonedas()){%>
                                <option  value="<%=m.getTipo_cambio()%>"><%=m.getId()%> </option>
                            <%}%>          
                   </select>
                    </div>
                     <br />
                    <div class="fila encabezado"><button  style="margin-bottom: 15px">Realizar</button> </div>
    </form>
<%}else{%> 
 <%
                          DecimalFormat df2 = new DecimalFormat("#.##");
                          df2.setRoundingMode(RoundingMode.DOWN);  
%>
 <div class="fila encabezado"><b><p>Retiro Realizado </b></p></div>   
 <div class="fila"> Fecha :  <%=model.getMovimiento().getFecha()%></div>
 <div class="fila"> Nombre :  <%=model.getCuenta().getUsuario().toString()%></div>
 <div class="fila"> Monto :  <%=df2.format(model.getMovimiento().getRetiro().getMonto())%>
     <%=model.getMovimiento().getCuenta().getMoneda().getId()%></div>

 
<%}%>   
</div>
</div> 
        <div class="EspacioLogin"></div>
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
       values.put("Monto_R", new String[]{String.valueOf(model.getRetiro().getMonto())});
       return values;
    }
    
%> 