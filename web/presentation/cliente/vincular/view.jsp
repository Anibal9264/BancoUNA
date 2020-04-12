<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banca.presentation.Cliente.vincular.Model"%>
<%@page import="banca.logic.Cuenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@ include file="/presentation/Head.jsp" %>
     <title> Cliente Vincular</title>
    </head>
     <%@ include file="/presentation/Header.jsp" %>
     
     <%@ include file="/presentation/Toolbar.jsp" %>
<%if(cliente!=null){%>
    <%
    Model model = (Model) request.getAttribute("model");
    Cuenta cuenta = model.getSeleccionado();
    Map<String,String> errores = (Map<String,String>) request.getAttribute("errores");
    Map<String,String[]> form = (errores==null)? this.getForm(model):request.getParameterMap();
%>


<div class="Espacio"></div>
<div class="Espacio"></div>
<%if(cuenta != null){%>
<div class="fila">
<div class="EspacioLogin" id="loginP">
<div style=" background-color:#1F618D ">
<div style=" background-color: silver " class="fila encabezado"><p><b>Detalles de Cuenta</b></p></div>
            <div class="fila encabezado" style=" color: lightgray"><p><b>Cuenta numero: <%=cuenta.getNumero()%></b></p></div>
            <div class="fila encabezado" style=" color: lightgray"><p><b>Cliente: <%=cuenta.getUsuario().toString()%></b></p></div>
        <br/>
        <div class="fila encabezado">
        <a href="/BancoUNA/presentation/cliente/vincular/vincular" <%
            session.setAttribute("Ncuenta",cuenta.getNumero());
        %> > 
        <button  id="ingresar" class="button" >Vincular</button></a>
            
        <a href="/BancoUNA/presentation/cliente/vincular/Cancelar" > 
           <button style=" align-content:center" id="ingresar"  >Cancelar</button></a></div>
</div>
</div>
</div>
<%}else{%>
<div class="fila">
<div class="EspacioLogin" id="loginP">
<div class="vincular">
    <form style=" background-color: #1F618D" action="/BancoUNA/presentation/cliente/vincular/Buscar" method="post">
        <div class="fila" id="vincular" style=" background-color: silver "><p><b>Vincular Cuenta</b></p></div>
        <div class="fila">
            <div class="etiqueta">Numero de cuenta a vincular</div>
            <div class="campo"><input class="<%=erroneo("cuenta_vincular", errores)%>"
                                      placeholder="Numero de cuenta" type="text" name="cuenta_vincular" 
                                      value="<%=form.get("cuenta_vincular")[0]%>"
                                      title="<%=title("cuenta_vincular", errores)%>" required></div>
        </div>
        <br />
        <div class="fila encabezado"><button id="ingresar" style="margin-bottom: 15px">Buscar</button> </div>
    </form>
</div>
</div>
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
       if(model.getSeleccionado() != null){
          values.put("cuenta_vincular", new String[]{model.getSeleccionado().toStringValor0()});
         }else{
         values.put("cuenta_vincular", new String[]{""});
         }
      return values;
    }
    
%> 
