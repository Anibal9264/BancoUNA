<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="banca.presentation.cuentas.Model"%>
<%@page import="banca.logic.Cuenta"%>
<%
    Model model = (Model) request.getAttribute("model");
    Cuenta cuenta = model.getSeleccionado();
    Map<String,String> errores = (Map<String,String>) request.getAttribute("errores");
    Map<String,String[]> form = (errores==null)? this.getForm(model):request.getParameterMap();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@ include file="/presentation/Head.jsp" %>
     <title>Login</title>
    </head>
     <%@ include file="/presentation/Header.jsp" %>
     <%@ include file="/presentation/Toolbar.jsp" %>
<%if(cuenta.getNumero()!=0){%>
<body>
        <div class="fila encabezado"><b><p>Detales de Cuenta</b></p></div>
            <div class="fila encabezado"><b><p>Cuenta numero: <%=cuenta.getNumero()%></b></p></div>
            <div class="fila encabezado"><b><p>Ciente: <%=cuenta.getUsuario().toString()%></b></p></div>
        <br/>
        <a href="/BancoUNA/presentation/cliente/cuentas/vincular" <%
            session.setAttribute("Ncuenta",cuenta.getNumero());
        %> > 
            <button class="button" >Vincular</button></a>
        <a href="/BancoUNA/presentation/cliente/cuentas/Cancelar"> 
            <button class="button" >Cancelar</button></a> </a>
</body>
<%}else{%>
<div id="vincular" class="columna">
    <form action="/BancoUNA/presentation/cliente/cuentas/vincularBuscar" method="post">
        <div class="fila encabezado"><b><p>Vincular Cuenta</b></p></div>
        <div class="fila">
            <div class="etiqueta">Numero de cuenta a vincular</div>
            <div class="campo"><input class="<%=erroneo("cuenta_vincular", errores)%>" placeholder="Numero de cuenta" type="text" name="cuenta_vincular" value="<%=form.get("cuenta_vincular")[0]%>" title="<%=title("cuenta_vincular", errores)%>"></div>
        </div>
        <br />
        <div class="fila encabezado"><button  style="margin-bottom: 15px">Buscar</button> </div>
    </form>
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
       values.put("cuenta_vincular", new String[]{model.getSeleccionado().toStringValor0()});
       return values;
    }
    
%> 
