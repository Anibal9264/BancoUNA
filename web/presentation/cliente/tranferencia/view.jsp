<%@page import="banca.presentation.login.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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

    <% Model model = (Model) request.getAttribute("model"); %>
    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>
    <body>
        <div class="fila">
            <div class="EspacioLogin"></div>
            <div class="EspacioLogin" id="loginP">
                <div class="login">
                    <img src="/BancoUNA/images/logoenlinea.png">
                </div>
                <div id="id" class="login">
                    <form action="URL" method="post">
                        <div class="fila encabezado"><b><p>Transferencia</b></p></div>


                        <div class="fila">
                            <div class="etiqueta">Cedula</div>
                            <input id="chekCed" type="checkbox" onchange="document.getElementById('cedula_t').disabled = !this.checked,
                document.getElementById('chekNum').disabled = this.checked;"/>
                            <input type="text" placeholder="Cedula" name="cedula_t" id="cedula_t" disabled
                                   class="<%=erroneo("cedula_t", errores)%>"
                                   value="<%=form.get("cedula_t")[0]%>"
                                   title="<%=title("cedula_t", errores)%>"
                                   >
                        </div>
                        <br>
                        <div class="fila">
                            <div class="etiqueta">numero</div>
                            <input id="chekNum" type="checkbox" onchange="document.getElementById('numero_t').disabled = !this.checked,
                             document.getElementById('chekCed').disabled = this.checked;"/>
                            <input type="text" placeholder="numero" name="numero_t" id="numero_t" disabled
                                   class="<%=erroneo("numero_t", errores)%>"
                                   value="<%=form.get("numero_t")[0]%>"
                                   title="<%=title("numero_t", errores)%>">
                        </div>     
                        <br />
                        <div class="fila">
                            <div class="etiqueta">Monto a trasferir</div>
                            <div class="campo"><input class="<%=erroneo("monto_t", errores)%>" placeholder="Monto" type="text" name="monto_t" value="<%=form.get("monto_t")[0]%>" title="<%=title("monto_t", errores)%>"required></div>
                        </div>
                        <br />
                        <div class="fila">
                            <div class="etiqueta">Nombre Depositante</div>
                            <div class="campo"><input class="<%=erroneo("Depocitante_t", errores)%>" placeholder="Depositante" type="text" name="Depocitante_t" value="<%=form.get("Depocitante_t")[0]%>" title="<%=title("Depocitante_t", errores)%>"required></div>
                        </div>
                        <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
                    </form>
                </div> 
            </div>
            <div class="EspacioLogin"></div>
        </div>
    </body>
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
       values.put("cedula_login", new String[]{model.getUser().getCedula()});
       values.put("pass_login", new String[]{model.getUser().getContraseña()});
       return values;
    }
    
%> 
