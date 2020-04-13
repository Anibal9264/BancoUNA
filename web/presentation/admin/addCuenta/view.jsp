
<%@page import="banca.presentation.Admin.addCuenta.Model"%>
<%@page import="banca.logic.Moneda"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>AddCuenta</title>
    </head>
    <%@ include file="/presentation/admin/Header.jsp" %>
    <%@include file="/presentation/admin/Toolbar.jsp" %>
    <br>
    <%if(admin!=null){%>
    <% Model model = (Model) request.getAttribute("model"); %>
    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>
   
    <div style=" background-color:#943126">
    <div class="EspacioLogin"></div>
    <div class="EspacioLogin" id="loginP">
    <div class="FormAdd">
    <%if(!model.getCuenta().isEstado()){%>
        <div class="fila encabezado"><p><b>Agregar Cuenta</b></p></div>
                <form action="/BancoUNA/presentation/admin/addCuenta/add" method="post" >
                   <div class="fila">
                        <div class="etiqueta">Cedula :
                        <div class="campo"><input style=" border-radius: 6px" placeholder="601230123" type="text" name="Cedula_C" 
                                                    value="<%=form.get("Cedula_C")[0]%>" 
                                                    class="<%=erroneo("Cedula_C", errores)%>"
                                                    title="<%=title("Cedula_C", errores)%>"required>
                        </div>
                         </div>
                    </div>
                    <br>
                    
                    <div class="fila">
                        <div class="etiqueta"> Moneda :
                        <select name="Moneda_C" id="Moneda_C">
                           <%for(Moneda m:model.getMonedas()){%>
                                <option  value="<%=m.getId()%>"><%=m.getId()%> </option>
                            <%}%>          
                        </select>
                         </div>
                    </div>
                        <br>
                        
                    <div class="fila">
                        <div class="etiqueta">Descripcion :
                        <div class="campo"><input style=" border-radius: 6px" placeholder="Cuenta Corriente" type="text" name="Descripcion_C" 
                                                    value="<%=form.get("Descripcion_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                   
                    <div class="fila">
                        <div class="etiqueta">Limite Diario:
                        <div class="campo"><input style=" border-radius: 6px" placeholder="100" type="text" name="LimiteD_C" 
                                                    value="<%=form.get("LimiteD_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                    <div class="fila encabezado"><button id="ingresar" style="margin-bottom: 15px">Ingresar</button> </div>
                </form>
  
       <div class="EspacioLogin"></div>
    </div>
    </div>
    </div>
<%}else{%>
    <div style=" text-align: center">
      <div  class="fila encabezado"><b><p>Cuenta se Agrego Correctamente</p></b></div>
      <div class="fila">
        Cuenta Numero :<%=model.getCuenta().getNumero()%>
      </div>
      <div class="fila">
          Moneda  : <%=model.getCuenta().getMoneda().getId()%>
      </div>
      <div class="fila">
          <div class="fila encabezado">
              <a href="/BancoUNA/presentation/admin/deposito/view" 
                 <button id="ingresar" style="margin-bottom: 15px">Agregar Depósito</button> </div> </a>
      </div>
 <%}%>
 </div>
 </div>
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
       values.put("Cedula_C", new String[]{model.getUser().getCedula()});
       values.put("Descripcion_C", new String[]{model.getCuenta().getDescripcion()});
       values.put("LimiteD_C", new String[]{String.valueOf(model.getCuenta().getLimite())});
       return values;
    }   
%> 