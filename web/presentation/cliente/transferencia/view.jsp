<%@page import="java.util.List"%>
<%@page import="banca.logic.Cuenta"%>
<%@page import="banca.presentation.transferecias.Model"%>
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
    <% List<Cuenta> cuentas = model.getC_salida(); %>

    <div class="fila">
        <div class="EspacioLogin"></div>
        <div class="EspacioLogin" id="loginP">
            <div class="login">
                <img src="/BancoUNA/images/transferencia.png">
            </div>
            <div class="FormT">
                <form action="/BancoUNA/presentation/transferencia/transferencia" method="post">
                    <div class="fila encabezado"><b><p>Transferencia</b></p></div>
                    <div class="fila">
                        <div class="etiqueta">Transferir desde :
                        <select id="cuentasbox">
                           <%for(Cuenta c:cuentas){%>
                                <option value="<%=c.getNumero()%>"><%=c.toString()%> <a href="/BancoUNA/presentation/cliente/cuentas/detalles?numeroFld=<%=c.getNumero()%>"></option>
                            <%}%>          
                        </select>
                         </div>
                    </div>
                    
                    
                    <div class="fila">
                        <div class="etiqueta">Cedula</div>
                        <input id="chekCed" type="checkbox" onchange="document.getElementById('cedula_t').disabled = !this.checked,
                                        document.getElementById('chekNum').disabled = this.checked;"/>
                        
                        <input type="text" placeholder="Cedula" name="cedula_t" id="cedula_t" disabled
                               class="<%=erroneo("cedula_t", errores)%>"
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
                               title="<%=title("numero_t", errores)%>">
                    </div>     
                    <br />
                    <div class="fila">
                        <div class="etiqueta">Monto a trasferir</div>
                        <div class="campo"><input  placeholder="Monto" type="text" name="monto_t" value="<%=form.get("monto_t")[0]%>" required></div>
                    </div>
                    <br />
                    <div class="fila">
                        <div class="etiqueta">Nombre Depositante</div>
                        <div class="campo"><input placeholder="Depositante" type="text" name="depocitante_t" value="<%=form.get("depocitante_t")[0]%>"required></div>
                    </div>
                    <div class="fila">
                        <div class="etiqueta">Motivo</div>
                        <div class="campo"><input  placeholder="Motivo" type="text" name="motivo_t" value="<%=form.get("motivo_t")[0]%>"required></div>
                    </div>
                    <div class="fila encabezado"><button  style="margin-bottom: 15px">Ingresar</button> </div>
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
       
       values.put("monto_t", new String[]{String.valueOf(model.getM_destino().getDeposito().getMonto())});
       values.put("monto_t", new String[]{String.valueOf(model.getM_salida().getRetiro().getMonto())});
       values.put("depocitante_t", new String[]{model.getM_destino().getDeposito().getNombreDepositante()});
       values.put("motivo_t", new String[]{model.getM_destino().getDeposito().getMotivo()});
       return values;
    }
    
%> 
