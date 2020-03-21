package banca.presentation.cuentas;
import banca.logic.Cliente;
import banca.logic.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "ClienteCuentasController", urlPatterns = {
    "/presentation/cliente/cuentas/show",
    "/presentation/cliente/cuentas/detalles"})
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
        }          
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
    //===================CUENTAS SHOW========================
    public String show(HttpServletRequest request) {
        return this.showAction(request);
    }
    public String showAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("usuario");
try {        
            model.setCuentas(domainModel.cuentasFind(new Cliente(usuario.getCedula(),usuario.toString(),usuario)));
            return "/presentation/cliente/cuentas/View.jsp";
        } catch (Exception ex) {
            model.setCuentas(new ArrayList<>());
            return "/presentation/cliente/cuentas/View.jsp";
        }
    }
    //==================DETALLES DE CUENTAS=====================
    private String detalles(HttpServletRequest request) {
        this.cleanAction(request);
        return this.detallesAction(request);
   
    }
    private void cleanAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        HttpSession session = request.getSession(true);
        model.setCuentas(new ArrayList<>());    
    }
    
    private String detallesAction(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        model.setMovimientos(domainModel.MovimientosFind(Integer.parseInt(request.getParameter("numeroFld"))));
        return "/presentation/cliente/cuentas/View.jsp";
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