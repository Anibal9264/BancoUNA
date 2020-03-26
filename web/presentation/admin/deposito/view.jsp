
<%@page import="banca.logic.Cuenta"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banca.presentation.Admin.deposito.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Login</title>
    </head>
    <header>
        <div class="columna">
            <div class="logo">
                <a href="/BancoUNA/presentation/Index.jsp">
                    <img src="/BancoUNA/images/logo.png">
                </a>
            </div> 
        </div>
        <div class="hr"></div>
    </header>
    <%@include file="/presentation/Toolbar.jsp" %>
    <br>
    <% Model model = (Model) request.getAttribute("model"); %>
    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>
    <div class="fila">
        <div class="EspacioLogin"></div>
        <div class="EspacioLogin" id="loginP">
     <div class="FormT">
          <div class="fila encabezado"><b><p>Menu Deposito</b></p></div>
     <form action="/BancoUNA/presentation/admin/deposito/show" method="post">
      <div class="fila">
                        <div class="etiqueta">Cedula:</div>
                        <div class="campo"><input  placeholder="101230456" type="text" name="Cedula_C" 
                                                    
                                                    class="<%=erroneo("Cedula_C", errores)%>"
                                                    title="<%=title("Cedula_C", errores)%>"required>
                        </div>
           <div class="fila encabezado"><button  style="margin-bottom: 15px">Buscar</button> </div>
      </div>    
      </form>

     <form action="/BancoUNA/presentation/admin/deposito/add" method="post">
      <div class="fila">
              <div class="etiqueta"> Cuenta destino :
                  <select name="Cuenta_D" id="Cuenta_D" required>
                           <%for(Cuenta f:model.getCliente().getCuentas()){%>
                                <option  value="<%=f.getNumero()%>"><%=f.toStringFavorita()%> </option>
                            <%}%>          
                   </select>
               </div>               
      </div>
      <br>
    </form>
            </div> 
        </div>
        <div class="EspacioLogin"></div>
    </div>
    
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
       values.put("Cedula_C", new String[]{model.getCliente().getCedula()});
       return values;
    }
    
%> 