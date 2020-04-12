<%@page import="java.util.List"%>
<%@page import="banca.logic.Cuenta"%>
<%@page import="banca.presentation.Cliente.transferecias.Model"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Cliente Transferencias</title>
    </head>
    <%@ include file="/presentation/Header.jsp" %>
      
    <%@ include file="/presentation/Toolbar.jsp" %>
    
         <br><br>
   <% if(cliente!=null){ %>
    <% Model model = (Model) request.getAttribute("model"); %>
    <% Map<String, String> errores = (Map<String, String>) request.getAttribute("errores"); %>
    <% Map<String, String[]> form = (errores == null) ? this.getForm(model) : request.getParameterMap();%>
    <% List<Cuenta> cuentas = model.getC_salida(); %>
    <% List<Cuenta>Favoritas = model.getC_Favoritas(); %>
  
    <div class="fila">
        <div class="EspacioLogin" id="loginP">
                 <br>
            <img src="/BancoUNA/images/transferencia.png">
            <form action="/BancoUNA/presentation/transferencia/transferencia" method="post">
                <br>
                <div class="fila encabezado"><b>Transferencia</b></div>
                <div class="fila">
                        <div class="etiqueta">Cuenta de Salida :
                        <select name="Cuenta_S" id="Cuenta_S" required>
                           <%for(Cuenta c:cuentas){%>
                                <option value="<%=c.getNumero()%>"><%=c.toString()%> </option>
                            <%}%>          
                        </select>
                         </div>
                    </div>
                      <br />
                    <div class="fila">
                        <div class="etiqueta"> Cuenta destino :
                        <select name="Cuenta_F" id="Cuenta_F" required>
                           <%for(Cuenta f:Favoritas){%>
                                <option  value="<%=f.getNumero()%>"><%=f.toStringFavorita()%> </option>
                            <%}%>          
                        </select>
                         </div>
                    </div>

                    <br />
                    <div class="fila">
                        <div class="etiqueta">Monto a trasferir</div>
                        <div class="campo"><input  placeholder="Monto" type="text" name="monto_t" 
                                                    value="<%=form.get("monto_t")[0]%>" 
                                                    class="<%=erroneo("monto_t", errores)%>"
                                                    title="<%=title("monto_t", errores)%>" required>
                        </div>
                    </div>
                    <br />
                    <div class="fila">
                        <div class="etiqueta">Motivo</div>
                        <div class="campo"><input  placeholder="Motivo" type="text" name="motivo_t"  
                                                   value="<%=form.get("motivo_t")[0]%>"  required></div>
                    </div>
                    <br>
                    <div class="fila encabezado"><button id="ingresar" style="margin-bottom: 15px">Ingresar</button> </div>
                </form>
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
       values.put("monto_t", new String[]{String.valueOf(model.getM_salida().getRetiro().getMonto())});
       values.put("motivo_t", new String[]{model.getM_destino().getDeposito().getMotivo()});
       return values;
    }
    
%> 
