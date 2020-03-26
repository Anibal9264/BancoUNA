package banca.presentation.Admin.deposito;
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


 @WebServlet(name = "DepositoController", urlPatterns = {"/presentation/admin/deposito/show","/presentation/admin/deposito/add"})
public class Controller extends HttpServlet {
   protected void processRequest(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("model",new Model());
        String viewUrl="";
        switch(request.getServletPath()){
            case "/presentation/admin/deposito/show":
                viewUrl=this.show(request);
                break; 
            case "/presentation/admin/deposito/add":
                viewUrl=this.add(request);
                break; 
        } 
        request.getRequestDispatcher(viewUrl).forward( request, response); 
  }
   
   
       private String show(HttpServletRequest request) {
           CargarDatos(request);
           return this.showAction(request);
       }
        public String showAction(HttpServletRequest request){
        return "/presentation/admin/deposito/view.jsp"; 
        }
       private void CargarDatos(HttpServletRequest request) {
        Model model = (Model) request.getAttribute("model");
        banca.logic.Model domainModel = banca.logic.Model.instance();
        model.setMonedas(domainModel.getMonedas());
        String c = String.valueOf(request.getAttribute("Cedula_C"));
        if( c != null && validar(request).isEmpty() ){
             Usuario real = domainModel.usuarioFind(request.getParameter("Cedula_C"),"");
             model.setCliente(new Cliente(real.getCedula(),real.toString(),real));
             model.getCliente().setCuentas(domainModel.cuentasFind(model.getCliente()));
         }
        
       }
    private String add(HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
        Map<String,String> validar(HttpServletRequest request){
        Map<String,String> errores = new HashMap<>();
        banca.logic.Model domainModel = banca.logic.Model.instance();
        Usuario real = domainModel.usuarioFind(request.getParameter("Cedula_C"),"");
        if (real == null){
            errores.put("Cedula_C","Usuario NO Existe");
            request.setAttribute("errores",errores);
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
