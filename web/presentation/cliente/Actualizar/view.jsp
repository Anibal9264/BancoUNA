<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/presentation/Head.jsp" %>
        <title>Cliente Transferencias</title>
    </head>
    <%@ include file="/presentation/Header.jsp" %>
      
    <%@ include file="/presentation/Toolbar.jsp" %>
 <%if(cliente!=null){%>   
         <br><br>
  <div class="EspacioLogin"></div>
  <div  class="EspacioLogin" id="loginP">
               <div class="fila encabezado"><p><b>Actualizar Datos</b></p></div>

               <form  action="/BancoUNA/presentation/cliente/actualizar/update" method="post" >
                   <div class="fila">
                        <div class="etiqueta">Cedula :
                        <div class="campo"><input   style=" border-radius: 6px"
                                                    type="text" name="Cedula_C" 
                                                    value="<%=cliente.getCedula()%>" 
                                                    disabled
                                                    required>
                        </div>
                         </div>
                    </div>
                    <br>
                    
                    <div class="fila">
                        <div class="etiqueta">Nombre :
                        <div class="campo"><input style=" border-radius: 6px"  type="text" name="Nombre_C" 
                                                    value="<%=cliente.getNombre()%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                   
                    <div class="fila">
                        <div class="etiqueta">Apellido 1 :
                        <div class="campo"><input style=" border-radius: 6px"  type="text" name="Apll1_C" 
                                                    value="<%=cliente.getApellido1()%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                      
                      <div class="fila">
                        <div class="etiqueta">Apellido 2 :
                        <div class="campo"><input style=" border-radius: 6px" type="text" name="Apll2_C" 
                                                    value="<%=cliente.getApellido2()%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                      
                    <div class="fila">
                        <div class="etiqueta">Telefono :
                        <div class="campo"><input style=" border-radius: 6px"  type="text" name="Tel_C" 
                                                    value="<%=cliente.getTelefono()%>" required>
                        </div>
                        </div>
                    </div>
                      <br>
                    <div class="fila">
                        <div class="etiqueta">Contraseña :
                        <div class="campo"><input style=" border-radius: 6px" type="text" name="Pass_C" 
                                                    value="<%=cliente.getContraseña()%>" required>
                        </div>
                        </div>
                    </div>
                      
                      
                      <br>
                    <div class="fila encabezado"><button id="ingresar" >Actualizar</button> </div>
                    <br>
                </form>
            
    <div class="EspacioLogin"></div>
    
</div> 
</div>       
 <%}%>        
</html>
