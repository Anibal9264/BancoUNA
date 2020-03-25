<%@page import="banca.presentation.Cliente.login.Model"%>
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
               
               <img  src="/BancoUNA/images/logo.png">
            
            </a>
         </div> 
    </div>
    <div class="hr"></div>
    </header>
   <body>
       
       
        <% Model model= (Model) request.getAttribute("model"); %>
        <% Map<String,String> errores = (Map<String,String>) request.getAttribute("errores"); %>
        <% Map<String,String[]> form = (errores==null)? this.getForm(model):request.getParameterMap();%>
    
          <tbody > 
          <table>    
               <div class="EspacioLogin" id="loginP">
            <thead>
            <TR >
            <div class="login"> 
                     <img style=" margin: 25px" src="/BancoUNA/images/logoenlinea.png"></th>
                    </div>
                </thead>
               <TD id="td1" class="login">
          
                <div  id="id" class="login">
                     <form action="/BancoUNA/presentation/login/login" method="post">
                        
                         <br>
                         <div class="fila">
                             <div class="etiqueta">Cédula</div>
                       <div class="campo">
                        <input style=" border-radius: 8px "
                               class="<%=erroneo("cedula_login",errores)%>"  
                               placeholder="Cedula" type="text" name="cedula_login" 
                               value="<%=form.get("cedula_login")[0]%>" title="<%=title("cedula_login", errores)%>"required></div>
                         </div>
                         <br/>
                         <div class="fila">
                             <div class="etiqueta">Contraseña</div>
                             <div class="campo">
                                 <input style=" border-radius: 8px"
                                        class="<%=erroneo("pass_login", errores)%>" 
                                        placeholder="Contraseña" type="password" name="pass_login" 
                                        value="<%=form.get("pass_login")[0]%>" title="<%=title("pass_login", errores)%>"required></div>
                         </div>
                         <p>
                        <div class="fila encabezado">
                            <button id="ingresar">
                                <b>Ingresar</b></button></div>
                     </p>
                    </form>
                   </TD>
               </div>          
               <TD id="td2" class="login"></TD> 
        </TR>
        </tbody>
            </div>
            <div class="EspacioLogin"></div>  </TR>
    </table>
            
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
