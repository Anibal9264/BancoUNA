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
                     <img src="/BancoUNA/images/logoenlinea.png"></th>
                    </div>
                </thead>
               <TD WIDTH="250" 
                        HEIGHT="180" align="left" bgcolor="#1F618D">
          
                <div  id="id" class="login">
                     <form action="/BancoUNA/presentation/login/login" method="post">
                        
                         <br>
                         <div class="fila">
                             <div class="etiqueta">Cédula</div>
                       <div class="campo">
                        <input style=" border-radius: 8px "
                               class="<%=erroneo("cedula_login",errores)%>"  
                               placeholder="Cedula" type="text" name="cedula_login" 
                               value="<%=form.get("cedula_login")[0]%>" title="<%=title("cedula_login", errores)%>"></div>
                         </div>
                         <br/>
                         <div class="fila">
                             <div class="etiqueta">Contraseña</div>
                             <div class="campo">
                                 <input style=" border-radius: 8px"
                                        class="<%=erroneo("pass_login", errores)%>" 
                                        placeholder="Contraseña" type="password" name="pass_login" 
                                        value="<%=form.get("pass_login")[0]%>" title="<%=title("pass_login", errores)%>"></div>
                         </div>
                         <p>
                        <div class="fila encabezado">
                            <button  style=" border-radius: 4px "  
                                     style=" color: slategray" 
                                     style="margin-bottom: 15px">
                                <b>Ingresar</b></button> </div>
                     </p>
                    </form>
                   </TD>
               </div>          
               <TD WIDTH="180" HEIGHT="180">
                   <h2>
                       <img  WIDTH="500" HEIGHT="300" src="/BancoUNA/images/fondo1.jpg">
                   </h2></TD> 
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
