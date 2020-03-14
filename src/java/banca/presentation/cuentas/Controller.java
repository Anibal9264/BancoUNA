package banca.presentation.cuentas;
import banca.logic.Cliente;
import banca.logic.Cuenta;
import banca.logic.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ClienteCuentasController", urlPatterns = {
    "/presentation/cliente/cuentas/show",
    "/presentation/cliente/cuentas/detalles",
    "/presentation/cliente/cuentas/vincularBuscar",
    "/presentation/cliente/cuentas/vincularshow",
    "/presentation/cliente/cuentas/vincular"})
public class Controller extends HttpServlet {
    
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {

        request.setAttribute("model",new Model());
        String viewUrl="";     
        switch (request.getServletPath()) {
          case "/presentation/cliente/cuentas/show":
              viewUrl = this.show(request);
              break;
          case "/presentation/cliente/cuentas/detalles":
              viewUrl = this.detalles(request);
              break;
          case "/presentation/cliente/cuentas/vincularBuscar":
              viewUrl = this.vincularBuscar(request);
              break;
          case "/presentation/cliente/cuentas/vincularshow":
              viewUrl = this.vincularShow(request);
          break;
          case "/presentation/cliente/cuentas/vincular":
              viewUrl = this.vincular(request);
          break;
        }          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
    //CUENTAS---------------------------------------------------------------
    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }
    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cliente cliente;
        try {
            cliente = domainModel.clienteFind(usuario);
        } catch (Exception ex) {
            cliente=null;
        }
        try {        
            model.setCuentas(domainModel.cuentasFind(cliente));
            return "/presentation/cliente/cuentas/View.jsp";
        } catch (Exception ex) {
            return "";
        }
    }
    //DETALLES DE CUENTAS------------------------------------------------------
    private String detalles(HttpServletRequest request) {
       try{
        Map<String,String> errores =  this.validar(request);
        if(errores.isEmpty()){
                this.cleanAction(request);
                return this.detallesAction(request);
        }
            else{
                request.setAttribute("errores", errores);
                return "/presentation/cliente/datos/View.jsp"; 
        }   
       }catch(Exception e){
            return "/presentation/Error.jsp";             
       }    
    }
    
    
    
    private void cleanAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        HttpSession session = request.getSession(true);
        model.setCuentas(new ArrayList());    
    }
    
    private String detallesAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        model.setMovimientos(domainModel.MovimientosFind(Integer.parseInt(request.getParameter("numeroFld"))));
        return "/presentation/cliente/cuentas/View.jsp";
    }
   
    //VINCULAR CUENTAS------------------------------------------------------
    private String vincularShow(HttpServletRequest request) {
         return this.VincularshowAction(request);
    }
     private String VincularshowAction(HttpServletRequest request) {
       banca.presentation.cuentas.Model model= (banca.presentation.cuentas.Model) request.getAttribute("model");
       model.setSeleccionado(new Cuenta());
       model.getSeleccionado().setNumero(0);
        return "/presentation/cliente/cuentas/vincular.jsp";
     }

      void updateModel(HttpServletRequest request){
       Model model= (Model) request.getAttribute("model");
       model.getSeleccionado().setNumero(Integer.parseInt(request.getParameter("cuenta_vincular")));  
     }
    
    
    private String vincularBuscar(HttpServletRequest request) {
        try{
        Map<String,String> errores =  this.validar(request);
        if(errores.isEmpty()){
                return this.VincularBuscarAction(request);
        }
        else{
         request.setAttribute("errores", errores);
         return "/presentation/cliente/cuentas/vincular.jsp"; 
        }   
       }catch(Exception e){
            return "/presentation/cliente/cuentas/vincular.jsp";            
       }    
       
     }
     
     private String VincularBuscarAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
      try {
          String n = request.getParameter("cuenta_vincular");
          model.setSeleccionado(domainModel.CuentaFind(Integer.parseInt(n)));
      } catch (Exception ex) {
          model.setSeleccionado(null);
      }
        return "/presentation/cliente/cuentas/vincular.jsp";
     }
     
     private String vincular(HttpServletRequest request) {
       return this.vincularAction(request);
    
    }

     private String vincularAction(HttpServletRequest request) {
       banca.logic.Model domainModel = banca.logic.Model.instance();
       HttpSession session = request.getSession(true);
       Usuario user = (Usuario)session.getAttribute("usuario");
       int n = (int) session.getAttribute("Ncuenta");
      try {
          domainModel.AgregarFavorita(n,user.getCedula());
      } catch (Exception ex) {
        return "/presentation/cliente/cuentas/vincular.jsp";  
      }
        return "/presentation/Index.jsp";
     }
     
     
     
     
     // COMPARTIDOS------------------------------------------------------------
     Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("cuenta_vincular").isEmpty()){
            errores.put("cuenta_vincular","Cuenta no valida");
        }
        return errores;
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

    

   
    

    

   

    

    

    

    
    
}