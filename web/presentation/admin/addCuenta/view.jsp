
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
    <%@ include file="/presentation/Header.jsp" %>
    <%@include file="/presentation/Toolbar.jsp" %>
    <br>
    <%if(admin!=null){%>
    <% Model model = (Model) request.getAttribute("model"); %>
    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>
    <%if(!model.getCuenta().isEstado()){%>
    <div class="fila">
        <div class="EspacioLogin"></div>
        <div class="EspacioLogin" id="loginP">
           
            <div class="FormAdd">
                <form action="/BancoUNA/presentation/admin/addCuenta/add" method="post" >
                    <div class="fila encabezado"><b><p>Agregar Cuenta</b></p></div>
                    
                    <div class="fila">
                        <div class="etiqueta">Cedula :
                        <div class="campo"><input  placeholder="601230123" type="text" name="Cedula_C" 
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
                        <div class="campo"><input  placeholder="Cuenta Corriente" type="text" name="Descripcion_C" 
                                                    value="<%=form.get("Descripcion_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                   
                    <div class="fila">
                        <div class="etiqueta">Limite Diario:
                        <div class="campo"><input  placeholder="100" type="text" name="LimiteD_C" 
                                                    value="<%=form.get("LimiteD_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                    <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
                </form>
            </div> 
        </div>
        <div class="EspacioLogin"></div>
    </div>
<%}else{%>
      ESTO TIENE QUE SALIR EN EL CENTRO Y MOSTRAR ESTOS DATOS :
      <div class="fila encabezado"><b><p>Cuenta se Agrego Correctamente</b></p></div>
      <div class="fila">
        Cuenta Numero :<%=model.getCuenta().getNumero()%>
      </div>
      <div class="fila">
          Moneda  : <%=model.getCuenta().getMoneda().getId()%>
      </div>
      <div class="fila">
          <div class="fila encabezado">
              <a href="/BancoUNA/presentation/admin/addCuenta/view" 
                 <button  style="margin-bottom: 15px">Agregar Deposito</button> </div> </a>
      </div>
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
       values.put("Cedula_C", new String[]{model.getUser().getCedula()});
       values.put("Descripcion_C", new String[]{model.getCuenta().getDescripcion()});
       values.put("LimiteD_C", new String[]{String.valueOf(model.getCuenta().getLimite())});
       return values;
    }   
%> 