package banca.presentation.transferecias;
import banca.logic.Cliente;
import banca.logic.Usuario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TransferenciasController", urlPatterns = {"/presentation/transferencia/show","/presentation/transferencia/transferencia"})
public class Controller extends HttpServlet {

  protected void processRequest(HttpServletRequest request,HttpServletResponse response)
         throws ServletException, IOException {
        request.setAttribute("model",new Model()); 
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/transferencia/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/transferencia/transferencia":
                viewUrl=this.transferencia(request);
                break;               
        }
        request.getRequestDispatcher(viewUrl).forward(request, response); 
  }

    public String show(HttpServletRequest request){
        Cargar_Datos_de_Salida(request);
        return this.showAction(request);
    }
    public String showAction(HttpServletRequest request){
        return "/presentation/cliente/transferencia/view.jsp"; 
    }
    
    private void Cargar_Datos_de_Salida(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente;
        try {
            cliente = new Cliente(usuario.getCedula(),usuario.toString(),usuario);
        } catch (Exception ex) {
            cliente=null;
        }
        try {        
           model.setC_salida(domainModel.cuentasFind(cliente));
        } catch (Exception ex) {
           
        }
    }
    
    private String transferencia(HttpServletRequest request) {
    try{
        Map<String,String> errores =  this.validar(request);
        if(errores.isEmpty()){
                return this.transferenciaAction(request);
        }else{
                request.setAttribute("errores", errores);
                return "/presentation/cliente/transferencia/view.jsp"; 
        }   
       }catch(Exception e){
            return "/presentation/Error.jsp";             
       }  
    }
    
   private Map<String, String> validar(HttpServletRequest request) {
   Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario real = (Usuario) session.getAttribute("usuario");
     if(!real.getIs()){
        if(!request.getParameter("cedula_t").isEmpty() ){
            if(revisarFavoxced(request.getParameter("cedula_t"),real.getCedula())){
                return errores;
            }else{
                 errores.put("cedula_t","Cuenta No Vinculada");
                 return errores;
            }
         }else if(!request.getParameter("numero_t").isEmpty()){
            if(revisarFavoxNum(request.getParameter("cedula_t"),request.getParameter("numero_t"))){
                return errores;
            }else{
                 errores.put("numero_t","Cuenta No Vinculada");
                 return errores;
            }
         }
     }else{
          if(!request.getParameter("cedula_t").isEmpty() ){
            if(revisarxced(request.getParameter("cedula_t"))){
                return errores;
            }else{
                 errores.put("cedula_t","Cuenta No Vinculada");
                 return errores;
            }
         }else if(!request.getParameter("numero_t").isEmpty()){
            if(revisarxNum(request.getParameter("numero_t"))){
                return errores;
            }else{
                 errores.put("numero_t","Cuenta No Vinculada");
                 return errores;
            }
         }
     }
   if (request.getParameter("cedula_t").isEmpty() ){
            errores.put("cedula_t","Cedula Requerida");
       }
      if (request.getParameter("numero_t").isEmpty() ){

            errores.put("numero_t","Numero Requerido");
       }
        return errores;
   }
    private String transferenciaAction(HttpServletRequest request) {
       banca.logic.Model domainModel = banca.logic.Model.instance();
       HttpSession session = request.getSession(true);
       Usuario user = (Usuario)session.getAttribute("usuario");
       
       ///////////////////////////////
       return null;
    }

    
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean revisarFavoxced(String cedF,String cedC) {
      banca.logic.Model domainModel = banca.logic.Model.instance();
      if(domainModel.FavoritaFindxCed(cedF,cedC) != null){return true;
      }else{return false;}
    }

    private boolean revisarFavoxNum(String ced,String num) {
     banca.logic.Model domainModel = banca.logic.Model.instance();
      if(domainModel.FavoritaFind(ced,num) != null){return true;
      }else{return false;}
    }
// ESTOS  SON PARA VERIFICAR DE CAJERO
    private boolean revisarxced(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean revisarxNum(String parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

 

   

}  
