<%@page import="banca.presentation.Admin.addUser.Model"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>AddUser</title>
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
    
<%if(model.getUser().getContraseña().isEmpty()){%>
    <div class="fila">
        <div class="EspacioLogin"></div>
        <div class="EspacioLogin" id="loginP">
           
            <div class="FormAdd">
                <form action="/BancoUNA/presentation/admin/addUser/add" method="post" >
                    <div class="fila encabezado"><b><p>Agregar Usuario</b></p></div>
                    
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
                        <div class="etiqueta">Nombre :
                        <div class="campo"><input  placeholder="Juan" type="text" name="Nombre_C" 
                                                    value="<%=form.get("Nombre_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                   
                    <div class="fila">
                        <div class="etiqueta">Apellido 1 :
                        <div class="campo"><input  placeholder="Solís" type="text" name="Apll1_C" 
                                                    value="<%=form.get("Apll1_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                      
                      <div class="fila">
                        <div class="etiqueta">Apellido 2 :
                        <div class="campo"><input  placeholder="Solís" type="text" name="Apll2_C" 
                                                    value="<%=form.get("Apll2_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                      
                    <div class="fila">
                        <div class="etiqueta">Telefono :
                        <div class="campo"><input  placeholder="88888888" type="text" name="Tel_C" 
                                                    value="<%=form.get("Tel_C")[0]%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                      
                    <div class="fila">
                        <div class="etiqueta"> Es :
                        <select name="Is_C" id="Is_C">
                           <option  value="Cliente">Cliente</option>
                           <option  value="Admin">Admin</option>
                        </select>
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
      <div class="fila encabezado"><b><p>Usuario se Agrego Correctamente</b></p></div>
      <div class="fila">
          <%=model.getUser().toString()%>
      </div>
      <div class="fila">
          Cedula : <%=model.getUser().getCedula()%>
      </div>
      <div class="fila">
          Contraseña : <%=model.getUser().getContraseña()%>
      </div>
      <div class="fila">
          <div class="fila encabezado" >
               <a href="/BancoUNA/presentation/admin/addCuenta/show"><button  style="margin-bottom: 15px">Agregar Cuenta</button> </a>
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
       values.put("Nombre_C", new String[]{model.getUser().getNombre()});
       values.put("Apll1_C", new String[]{model.getUser().getApellido1()});
       values.put("Apll2_C", new String[]{model.getUser().getApellido2()});
       values.put("Tel_C", new String[]{model.getUser().getTelefono()});
       return values;
    }   
%> 
