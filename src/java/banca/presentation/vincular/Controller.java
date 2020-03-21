package banca.presentation.vincular;

import banca.logic.Cuenta;
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

@WebServlet(name = "VincularController", urlPatterns = {
    "/presentation/cliente/vincular/show",
    "/presentation/cliente/vincular/Buscar",
    "/presentation/cliente/vincular/vincular"})
public class Controller extends HttpServlet {
    
  protected void processRequest(HttpServletRequest request, 
                                HttpServletResponse response)
         throws ServletException, IOException {

        request.setAttribute("model",new Model());
        String viewUrl="";     
        switch (request.getServletPath()) {
          case "/presentation/cliente/vincular/show":
              viewUrl = this.vincularShow(request);
          break;
          case "/presentation/cliente/vincular/Buscar":
              viewUrl = this.vincularBuscar(request);
              break;
          case "/presentation/cliente/vincular/vincular":
              viewUrl = this.vincular(request);
          break;
        }          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
  //========================SHOW==================================
    private String vincularShow(HttpServletRequest request) {
         return this.VincularshowAction(request);
    }
    private String VincularshowAction(HttpServletRequest request) {
        return "/presentation/cliente/vincular/view.jsp";
     }
//======================BUSCAR===================================
    private String vincularBuscar(HttpServletRequest request) {
        try{
        Map<String,String> errores =  this.validar(request);
        if(errores.isEmpty()){
                return this.VincularBuscarAction(request);
        }
        else{
         return "/presentation/cliente/vincular/view.jsp"; 
        }   
       }catch(Exception e){
            return "/presentation/cliente/vincular/view.jsp";            
       }    
       
     }
    
private  Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        Cuenta cuenta = domainModel.CuentaFind(Integer.valueOf(request.getParameter("cuenta_vincular")));
        if(cuenta== null){
              errores.put("cuenta_vincular","Cuenta no Existe");
              request.setAttribute("errores", errores);
          }        
         if (domainModel.FavoritaFind(usuario.getCedula(),request.getParameter("cuenta_vincular")) != null){
            errores.put("cuenta_vincular","YA ESTA VINCULADA");
            request.setAttribute("errores", errores);
          }
         if (cuenta != null && cuenta.getUsuario().getCedula().equals(usuario.getCedula())){
            errores.put("cuenta_vincular","ESTA CUENTA TE PERTENECE");
            request.setAttribute("errores", errores);
          }
        return errores;
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
        return "/presentation/cliente/vincular/view.jsp";
     }
     
  //=======================VINCULAR LA CUENTA =====================
     
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
        return "/presentation/cliente/vincular/view.jsp";  
      }
        return "/presentation/Index.jsp";
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
